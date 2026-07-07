package location;
/**
 *
 * @author karan
 */
// Output of data
public class Result {
    // Total distance in meters
    public double distanceM;
    // Total time it takes in seconds
    public double durationS;
    // Type of transport process (driving, walking, cycling
    public String method;
    // The users and items coordinates (lat, ln) -> (x, y)
    public double[] user;
    public double[] item;
}
