package location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 *
 * @author karan
 */
// Maps OSMR json thats needed
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteResponse {
    public Route[] routes; 
    static class Route { 
        // the distance and duration of the calculation
        public double distance; 
        public double duration; 
    }
}

