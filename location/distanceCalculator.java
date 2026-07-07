package location;

// Java Imports
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.Duration;
/**
 *
 * @author karan
 */
// Rest resource 
@Path("location")
// Returns json 
@Produces(MediaType.APPLICATION_JSON)
public class distanceCalculator {
    // OSMR server URL
    private static final String Query1 = "http://router.project-osrm.org";
    // Configured HTTP client
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();
    
    private static final ObjectMapper MAPPER = new ObjectMapper()
            
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // This function takes the users input for user and item (logitude, latitude) and the method of transport (walking, drving, cycling
    // This also consists of validation ensuring inputs are entered correctly and to ensure to issues occur
    @GET
    // Contains all necessary inputs the coordinated of the user and item + the method of transport
    public Response getProximity(
            @QueryParam("userLatitude") double userLatitude,
            @QueryParam("userLongitude") double userLongitude,
            @QueryParam("itemLatitude")   double itemLatitude,
            @QueryParam("itemLongitude")   double itemLongitude,
            @DefaultValue("driving") @QueryParam("method") String method
    ) {
        // This validates the entered method for transportation ensuring it is not entered incorrectly
        // Only accepts (driving, walking and cycling
        if (!("driving".equals(method) || "walking".equals(method) || "cycling".equals(method))) {
            // Error message is displayed to avoid the program breaking
            return Response.status(Response.Status.BAD_REQUEST).entity(Error("The selected method of transport you have entered is incorrect make sure its the following (driving, walking, cycling)")).build();
        }
        // Longitude validation
        if (userLongitude < -180 || userLongitude > 180 || itemLongitude < -180 || itemLongitude > 180) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Error("Longitude is incorrect it must be between the following valude (-180 to 180)")).build();
        }
        // Latitude validation
        if (userLatitude < -90 || userLatitude > 90 || itemLatitude < -90 || itemLatitude > 90) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Error("Latitude is incorrect it must be between the following values(-90 to 90)")).build();
        }
        
        // Try is used to ensure the program is running safely (making sure it does not break from both program and user error)
        try {
            // OSRM expects from user
            String path = String.format("/route/v1/%s/%f,%f;%f,%f", method, userLongitude, userLatitude, itemLongitude, itemLatitude);
            // Hard coded to avoid any extra inputs
            String qs = "overview=false"; 
            URI uri = URI.create(Query1 + path + "?" + qs);
            
            // Builds the HTTP GET request, again it has a timeout to avoide connection issues
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            //HttpResponse<String> response = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response = Retry(request);
            
            // Validation for if there is no valid input or no 200 response 
            if (response.statusCode() != 200 || response.body() == null || response.body().isBlank()) {
                // Displays an error message notifying the issue with the response
                return Response.status(Response.Status.BAD_GATEWAY).entity(Error("Error: OSRM or empty response")).build();
            }

            // Deserialize Week 3 
            RouteResponse osrm = MAPPER.readValue(response.body(), RouteResponse.class);
            // Validates the structure
            if (osrm == null || osrm.routes == null || osrm.routes.length == 0) {
                return Response.status(Response.Status.BAD_GATEWAY).entity(Error("Error: No routes")).build();
            }
            // Extraction of the distance (Meters) and duration (Seconds)
            double distance = osrm.routes[0].distance; 
            double duration = osrm.routes[0].duration; 
            // Returns what client neeeds
            Result result = new Result();
            result.distanceM = distance;
            result.durationS = duration;
            result.method = method;
            // Coordinates of user and item location (x,y)
            result.user = new double[]{userLatitude, userLongitude};
            result.item   = new double[]{itemLatitude, itemLongitude};
            // Convorts to json
            String json = MAPPER.writeValueAsString(result);
            return Response.ok(json, MediaType.APPLICATION_JSON).build();

        } catch (IOException error) {
            // Validates if any network failure or mapping
            return Response.status(Response.Status.BAD_GATEWAY).entity(Error("Error: Failed to read OSRM response")).build();
        
        }
        // Validates if thread was interrupted
         catch (Exception error) {
            // Runtime/timout exceptions
            return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(Error("Error: OSRM timed out")).build();
        
        }
    }
    // If the request doesnt work it retries (simple implementatiom)
    private HttpResponse<String> Retry(HttpRequest request)
            throws IOException, InterruptedException {
        HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
        // Validation for if retry is needed
        if (response.statusCode()==429 || (response.statusCode() >=500 && response.statusCode() < 599)) {
            // Waits to retry
            Thread.sleep(50);
            // Retrys the send
            response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
        }
        // Returns the response
        return response;
    }
    
    // Generates error
    private String Error(String message) {
        try {
            return MAPPER.writeValueAsString(new ErrorDTO(message));
        } catch (Exception ignore) {
            return "{\"error\":\"" + message + "\"}";
        }
    }
}