package location;

import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.annotation.Priority;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author karan
 */
// Code limits the total requests a client can send (preventing DoS attacks and reduce RU consumption from 1 user)
@Provider
@Priority(Priorities.AUTHENTICATION) // This function is prioritised over all other codes
public class DosFilter implements ContainerRequestFilter{
    // Stores requests for client
    private static final ConcurrentHashMap<String, Window> BUCKETS = new ConcurrentHashMap<>();
    @Override
    public void filter(ContainerRequestContext context) {
        // Key identifies client
        String key = clientKey(context);
        // Current time
        long now = System.currentTimeMillis();
        // Gets the already exiting request for the selected client which is identified by the key
        Window window = BUCKETS.get(key);
        // this is an if statment which checks ig the window exists for client and if the window has expired in the current time stated
        if (window == null || now - window.start >= 120_000) {
            window = new Window(now);
            BUCKETS.put(key, window);
        }
        // Simple counter (client window)
        int count = window.count.incrementAndGet();
        // This validates whether the total requests are over the listed set max requests the system can handel
        if (count > 50) {
            // if the requests go over the set accepted requests an error will appear (ending submission of requests)
            context.abortWith(Response.status(429).type(MediaType.APPLICATION_JSON).entity("{\"error\":\"Too many requests are being sent at a time by the client\"}").build());
        }
    }   
    private static String clientKey(ContainerRequestContext context) {
        // Rate limit per api key (avoids request issues)
        String apiKey = context.getHeaderString("APIKey");
        // Validation checks if the api key header is empty or default
        if (apiKey == null || apiKey.isBlank()) {
            return "APIkey";
        }
        return apiKey.trim();
    }
    // Used to track the clients request
    // stores start time of window + number of requests created
    private static final class Window {
        // timestamp of window creation
        final long start;
        // number of requests within window
        final AtomicInteger count = new AtomicInteger(0);
        // Constructor
        Window(long start) {
            // start time of window
            this.start = start;
        }
    }
}
