package location;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
/**
 *
 * @author karan
 */
// Exposes a read only search endpoint for items stored in cosmos
// Returns in json
@Path("items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemsResource {
    // Used to serialise json
    // also prevents the failure if there are extra items in the db
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @GET
    //  cakks search item to query item container
    // The location filer and search of items
    public Response search(@QueryParam("search") String search, @QueryParam("location") String location, @QueryParam("available") Boolean available) {
        // Error handler to make sure if something breaks it doesnt cause big issues
        try {
            // checks cosmos for items (search or location)
            List<Item> items = Cosmos.searchItems(search, location, available);
            // Serialise object to json for http
            String json = MAPPER.writeValueAsString(items);
            return Response.ok(json).build();
        } catch (Exception e) {
            // Error if search fails
            e.printStackTrace();
            return Response.status(502)
                .type(MediaType.APPLICATION_JSON)
                .entity("{\"error\":\"Failed to get the items\"}")
                .build();
        }
    }
    // used if serialisation fails 
    private String Error(String message) {
        try {
            return MAPPER.writeValueAsString(new ErrorDTO(message));
        } catch (Exception e) {
            return "{\"error\":\"" + message.replace("\"", "'") + "\"}";
        }
    }
}
