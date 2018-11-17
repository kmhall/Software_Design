/**
 * AmazonOrder is a class that holds the data of an order
 */
public class AmazonOrder {

    private String address;
    private String city;
    private String state;
    private int zip;
    private String name;
    private String item;
    private String category;

    private Boolean terminatingKey;

    private int shippingCenterID;
    private int shippingSectionID;
    private int deliveryTruckID;

    /**
     * Constructs an AmazonOrder
     * @param order Array of order information
     */
    public AmazonOrder(String[] order){
        this.address = order[0];
        this.city = order[1];
        this.state = order[2];
        this.zip = Integer.parseInt(order[3]);
        this.name = order[4];
        this.item = order[5];
        this.category = order[6].replaceAll("\\s+","");

        this.shippingCenterID = 0;
        this.shippingSectionID = 0;
        this.deliveryTruckID = 0;
        this.terminatingKey = false;

    }

    /**
     * Converts an AmazonOrder to a string
     * @return String of order info
     */
    @Override
    public String toString() {
        return " Address: " + address + "| City: " + city + "| State: " + state + "| Zip:" + zip + "| Name: " + name + "| Item: "+ item + "| Category: "+ category;
    }

    /**
     * Sets the termination key. This key is sent when all orders are finished being processed.
     */
    public void setTerminatingKey() {
        this.terminatingKey = true;
    }

    /**
     * Gets the value of the terminating key.
     * @return Boolean, if the order is the terminating order.
     */
    public Boolean getTerminatingKey() {
        return terminatingKey;
    }

    /**
     * Gets the city for delivery
     * @return String of the city
     */
    public String getCity() {
        return city;
    }

    /**
     * The id of the shipping center the order went through
     * @return int of shipping center
     */
    public int getShippingCenterID() {
        return shippingCenterID;
    }

    /**
     * Set the id of the shipping center the package went through
     * @param shippingCenterID  Shipping center id
     */
    public void setShippingCenterID(int shippingCenterID) {
        this.shippingCenterID = shippingCenterID;
    }

    /**
     * The id of the shipping section the order went through
     * @return int of shipping center section
     */
    public int getShippingSectionID() {
        return shippingSectionID;
    }

    /**
     * Set the id of the shipping section the package went through
     * @param shippingSectionID  Shipping section id
     */
    public void setShippingSectionID(int shippingSectionID) {
        this.shippingSectionID = shippingSectionID;
    }

    /**
     * Gets the delivery truck ID
     * @return int of delivery truck id
     */
    public int getDeliveryTruckID() {
        return deliveryTruckID;
    }

    /**
     * Get the name on the order.
     * @return String of name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the address on the order.
     * @return String of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get the item on the order.
     * @return String of item
     */
    public String getItem() {
        return item;
    }

    /**
     *Set the delivery truck id
     * @param deliveryTruckID id of the delivery truck used
     */
    public void setDeliveryTruckID(int deliveryTruckID) {
        this.deliveryTruckID = deliveryTruckID;
    }

    /**
     * Get the category that the item belongs to
     * @return String of Category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Converts the order back to an array
     * @return String[] order at an Array
     */
    public String[] orderAsArray(){
        return new String[]{ this.address, this.city, this.state, Integer.toString(this.zip), this.name, this.item, this.category};
    }
}
