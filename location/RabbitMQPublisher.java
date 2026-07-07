package location;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 *
 * @author karan
 */
// used to publish the even messageto rabbit queue
// Passes the requst to the correct place 
public final class RabbitMQPublisher {
    // publishes to the queue using the routing key
    private static final String EXCHANGE = "";
    // Queue name for request
    private static final String QUEUE = "requests";
    private RabbitMQPublisher() {}
    // publish the request to the rabbit queue
    public static void publish(String body) {
        // reads details from EV
        String uri = System.getenv("env");
        // Regular validation to ensure uri does not contain nothing if it has nothing it will reply with an errror
        if (uri == null || uri.isBlank()) {
            System.out.println("Error: Missing values");
            return;
        }
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri(uri);
            // makes sure that the connection is closed
            try (Connection connection = factory.newConnection();
                    Channel channel = connection.createChannel()) {
                // Declare queue
                channel.queueDeclare(QUEUE, true, false, false, null);
                // Publish the request using queue name as route key
                channel.basicPublish(EXCHANGE, QUEUE, null, body.getBytes("UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
