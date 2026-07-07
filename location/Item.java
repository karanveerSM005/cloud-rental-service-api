package location;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *
 * @author karan
 */
// Model for items stored within cosmos (items container)
// Serialisation and deserialisation when communicating
// Jackson maps java names and cosmos item
// Note @JsonProperty simply specifies the name of a field within json
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    // The cosmos ID for item
    public String id;
    @JsonProperty("item_id")
    public String item_id;
    // ID of owner
    @JsonProperty("owner_id")
    public String owner_id;
    // Name of the item
    public String name;
    // Type of item eg. outside
    public String category;
    // Location of the item (where it is eg. Leeds)
    @JsonProperty("location")
    public String location;
    // Daily rate of the item 
    @JsonProperty("daily_rate")
    public double daily_rate;
    // If the item is available or not (can only be true/false)
    public boolean available;
    // The condition of well the item was stored in (eg. good, brand new)
    public String condition;
    // A breif description of the item 
    public String description;
    public Item() {
    }
}
