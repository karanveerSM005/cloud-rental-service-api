package location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.UUID;
/**
 *
 * @author karan
 */
// Creates requests, retreives request, cancel request
@Path("requests")
@Produces(MediaType.APPLICATION_JSON)
public class RequestsResource {
    // jackspon for json processing
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    // Creates request in json
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(String body, @Context UriInfo uriInfo) {
        try {
            // deserialise the json body into dto
            Input input = MAPPER.readValue(body, Input.class);
            // validation for inputs
            if (input == null || Blank(input.itemId) || Blank(input.renterId)) {
                return badRequest("Error: The Item ID or Renter ID are needed");
            }
            // Gen request ID
            String requestId = UUID.randomUUID().toString();
            // Creates request with pending status
            RentalRequest request = RentalRequest.Pending(requestId, input.itemId, input.renterId, input.startDate, input.endDate);
            // request to db
            Cosmos.Create(request);
            // Publishes asunchronously
            RabbitMQPublisher.publish(MAPPER.writeValueAsString(new EventMessage("request_created", requestId, request.status)));
            // Builds location header
            URI location = uriInfo.getAbsolutePathBuilder().path(requestId).build();
            return Response.created(location).entity(MAPPER.writeValueAsString(request)).build();
        } catch (Exception i) {
            // Error if everything fails
            i.printStackTrace();
            return badGateway("Error: Failed to create the request");
        }
    }
    
    // Updates the endpoint to status = cacelled
    // Patch simply replaces 1 field which in this case is replacing Pending -> Canceled
    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, String body) {
        try {
            // deserialise json
            Update input = MAPPER.readValue(body, Update.class);
            // validate if it contains nothign
            if (input == null || Blank(input.status)) {
                return badRequest("Error: Missing status");
            }
            // Only allow Cancelled (keeps it simple + safe)
            if (!"Cancelled".equalsIgnoreCase(input.status.trim())) {
                return badRequest("Error: Only allow Cancelled no other value");
            }
            RentalRequest request = Cosmos.Get(id);
            if (request == null) 
                return notFound("Error: Request not found");

            // cancelling a cancelled request stops potental error
            if ("Cancelled".equalsIgnoreCase(request.status)) {
                return Response.ok(MAPPER.writeValueAsString(request)).build();
            }
            
            RentalRequest updated = Cosmos.Cancel(id);
            if (updated == null) {
                return notFound("Error: Request not found");
            }
            // publishes cancellation
            RabbitMQPublisher.publish(MAPPER.writeValueAsString(new EventMessage("request_cancelled", id, updated.status)));
            // Simple validation
            return Response.ok(MAPPER.writeValueAsString(updated)).build();
        } catch (java.io.IOException i) {
            return badRequest("Error: Invalid JSON");
        } catch (Exception i) {
            i.printStackTrace();
            return badGateway("Error: Failed to update");
        }
    }
    
    // dto for patch
    static class Update {
        public String status;
    }
    
    // DTO for post
    static class Input {
        // Input entries (curl)
        public String itemId;
        public String renterId;
        public String startDate;
        public String endDate;
    }
    // Validates if nothing in entry box
    private boolean Blank(String input) {
        return input == null || input.isBlank();
    }
    // Errro for bad reqeusts
    private Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST).entity(Error(message)).build();
    }
    // Validation for not found 
    private Response notFound(String message) {
        return Response.status(Response.Status.NOT_FOUND).entity(Error(message)).build();
    }
    // Validation for bad gateway
    private Response badGateway(String message) {
        return Response.status(Response.Status.BAD_GATEWAY).entity(Error(message)).build();
    }
    // JSON error payload
    private String Error(String message) {
        try {
            return MAPPER.writeValueAsString(new ErrorDTO(message));
        } catch (Exception i) {
            return "{\"error\":\"" + message.replace("\"", "'") + "\"}";
        }
    }
}
