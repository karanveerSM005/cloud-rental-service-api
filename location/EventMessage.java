package location;

import java.time.Instant;
/**
 *
 * @author karan
 */
// Encapsulates event info
public class EventMessage {
    // Type of event (the category)
    public String type;
    // ID of request (of event)
    public String requestId;
    // status of event (current status eg. pending)
    public String status;
    // Timestamp of event (date when took place)
    public String timestamp;
    // Initalise EventMessage object
    public EventMessage() {}
    public EventMessage(String type, String requestId, String status) {
        // Asigns value of eg type paramater to type field of event message object
        this.type = type;
        this.requestId = requestId;
        this.status = status;
        this.timestamp = Instant.now().toString();
    }
}
