package location;
// Imports
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.annotation.Priority;
/**
 *
 * @author karan
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKey implements ContainerRequestFilter {
    // Simply retrieves the API key from enviroment variable 
    private static final String API = System.getenv("APIKEY");
    @Override
    public void filter(ContainerRequestContext context) {
        // Get Http method (request)
        String method = context.getMethod();
        // If a get method is used the API key method will be skipped as it is not a write method which does not physically effect the database
        if ("GET".equalsIgnoreCase(method)) 
            return;
        // Simple verification to ensure that he API key entry within curl is not empty
        if (API == null || API.isBlank()) {
            // In the case that there is no api key stored in the enviroment variable
            context.abortWith(Response.status(Response.Status.SERVICE_UNAVAILABLE).type(MediaType.APPLICATION_JSON).entity("{\"error\":\" missing APIKEY \"}").build());
            return;
        }
        // Gets the API key
        String key = context.getHeaderString("APIKey");
        // Verification to check if entered API key is valid
        if (key == null || !API.equals(key)) {
            // If not error message is displayed that the entered API key is incorrect
            context.abortWith(Response.status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON).entity("{\"error\":\"Invalid API key entered\"}").build());
        }
    }
}