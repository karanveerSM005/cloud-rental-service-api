package location;
// Required imports
import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.azure.cosmos.util.CosmosPagedIterable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author karan
 */
public final class Cosmos {
    // The Cosmos DB endpoint (now removed)
    private static final String ENDPOINT = removeHarcoded("ENDPOINT");
    // Contains the Primary Key (now removed)
    private static final String KEY = removeHarcoded("KEY");
    // Database
    private static final String DATABASE = "coursework";
    // Containers named as items and requests
    private static final String ITEMS_CONTAINER = "items";
    private static final String REQUESTS_CONTAINER = "requests";
    // Prevents instantiation 
    private Cosmos() {}
    // Variables for thread safety
    private static volatile CosmosClient CLIENT;
    private static volatile CosmosContainer ITEMS;
    private static volatile CosmosContainer REQUESTS;

    // Tis returns singleton of cosmos client 
    // note client, items and requests are pretty much the same code 
    private static CosmosClient client() {
        CosmosClient client = CLIENT;
        // If statment to identify if the client exists
        if (client == null) {
            // Synchroises access to cosmos (for safe threading)
            synchronized (Cosmos.class) {
                // Checks if client has been created
                client = CLIENT;
                if (client == null) {
                    // Builds lient with the endpoint, key
                    CLIENT = client = new CosmosClientBuilder().endpoint(ENDPOINT).key(KEY).consistencyLevel(ConsistencyLevel.SESSION).buildClient();
                }
            }
        }
        return client;
    }
    // Gets the items container from DB (look at client comments as they follow same structure)
    private static CosmosContainer Items() {
        CosmosContainer container = ITEMS;
        // IF statment to valdate if items exists
        if (container == null) {
            // synchronises access to cosmos
            synchronized (Cosmos.class) {
                container = ITEMS;
                if (container == null) {
                    // builds the item with the endpoint
                    ITEMS = container = client().getDatabase(DATABASE).getContainer(ITEMS_CONTAINER);
                }
            }
        }
        return container;
    }
    // Gets the Requests container from DB (look at client comments as they follow same structure)
    private static CosmosContainer Requests() {
        CosmosContainer container = REQUESTS;
        // If statment to identify if the request exists
        if (container == null) {
            // if it does it will synchronise access to cosmos db
            synchronized (Cosmos.class) {
                container = REQUESTS;
                if (container == null) {
                    // builds the request with endoopoint 
                    REQUESTS = container = client().getDatabase(DATABASE).getContainer(REQUESTS_CONTAINER);
                }
            }
        }
        return container;
    }
    // Items which identifies items from the Items container
    public static List<Item> searchItems(String search, String location, Boolean available) {
        CosmosContainer container = Items();
        // Construct the SQL query (available?)
        StringBuilder sql = new StringBuilder("SELECT * FROM c WHERE 1=1");
        // Holds sql parameters
        List<SqlParameter> parameters = new ArrayList<>();
        // Validates if the search is empty or not
        if (search != null && !search.isBlank()) {
            // Append sql query search itms name
            sql.append(" AND (CONTAINS(LOWER(c.name), LOWER(@search)) " + "OR CONTAINS(LOWER(c.category), LOWER(@search)))");
            parameters.add(new SqlParameter("@search", search));
        }
        // Check if location is empty 
        if (location != null && !location.isBlank()) {
            // Appends to filer (location)
            sql.append(" AND LOWER(c.location) = LOWER(@loc)");
            parameters.add(new SqlParameter("@loc", location));
        }
        // Available code to find all available items
        if (available != null) {
            sql.append(" AND c.available = @avail");
            parameters.add(new SqlParameter("@avail", available));
        }
        
        // Create new object
        SqlQuerySpec query = new SqlQuerySpec(sql.toString(), parameters);
        // Executes query 
        CosmosPagedIterable<Item> result = container.queryItems(query, new CosmosQueryRequestOptions(), Item.class);
        // New array to store the results found
        List<Item> output = new ArrayList<>();
        // add eacg item
        for (Item item : result) {
            output.add(item);
        }
        // outputs the objects
        return output;
    }

    // Create Request into the requests container
    public static void Create(RentalRequest request) {
        CosmosContainer container = Requests();
        // Creates the entry
        container.createItem(request, new PartitionKey(request.request_id), new CosmosItemRequestOptions());
    }
    
    // Just gets the request from its ID same function as searching the available items
    public static RentalRequest Get(String requestId) {
        CosmosContainer container = Requests();
        try {
            // Reads from the container
            return container.readItem(requestId, new PartitionKey(requestId), RentalRequest.class).getItem();
        } catch (CosmosException i) {
            if (i.getStatusCode() == 404) 
                return null;
            throw i;
        }
    }
    
    // Cancels the request in the requests container, however it updates the request to be canceled rather then removing it completly
    public static RentalRequest Cancel(String requestId) {
        CosmosContainer container = Requests();
        try {
            // Reads from the container
            RentalRequest request = container.readItem(requestId, new PartitionKey(requestId), RentalRequest.class).getItem();
            // This simply changes from pending to cancelled
            request.status = "Cancelled";
            container.upsertItem(request, new PartitionKey(request.request_id), new CosmosItemRequestOptions());
            return request;
        // Catches error if finds a 404 error
        } catch (CosmosException i) {
            if (i.getStatusCode() == 404) 
                return null;
            throw i;
        }
    }
    // Function Removes the vulnerability of hardcoded credentials
    private static String removeHarcoded(String credential) {
        // Retrieves enviroment variable (the cosmos Key and endpoint)
        String i = System.getenv(credential);
        // Basic validation to ensure the i variable is not empty 
        if (i == null || i.isBlank()) {
            // If it does the program will display an error message
            throw new IllegalStateException("Missing environment variable");
        }
        return i.trim();
    }
}
