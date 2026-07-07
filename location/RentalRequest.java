package location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.Instant;
/**
 *
 * @author karan
 */
// is DTO (Rest api + Cosmos DB)
// for json deserialisation (so db can read) and serialisation (responses)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentalRequest {
    // Cosmos fields for the rental container (note this was originally designed for the test data, but it still works perfectly fine with stress data)
    // These are self explained variables of the rental container items
    public String id;
    public String request_id;
    public String item_id;
    public String renterId;
    public String status;
    public String createdAt;
    public String startDate;
    public String endDate;
    public RentalRequest() {}
    
    public static RentalRequest Pending(String requestId, String itemId, String renterId, String startDate, String endDate) {
        RentalRequest request = new RentalRequest();
        // same details as cosmos db and api identifire
        request.id = requestId;
        request.request_id = requestId;
        request.item_id = itemId;
        request.renterId = renterId;
        // state to pending
        request.status = "Pending";
        // Timestamps
        request.createdAt = Instant.now().toString();
        request.startDate = startDate;
        request.endDate = endDate;
        return request;
    }
}
