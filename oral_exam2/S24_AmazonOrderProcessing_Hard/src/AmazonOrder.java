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

    @Override
    public String toString() {
        return " Address: " + address + "| City: " + city + "| State: " + state + "| Zip:" + zip + "| Name: " + name + "| Item: "+ item + "| Category: "+ category;
    }

    public void setTerminatingKey() {
        this.terminatingKey = true;
    }

    public Boolean getTerminatingKey() {
        return terminatingKey;
    }

    public String getCity() {
        return city;
    }

    public int getShippingCenterID() {
        return shippingCenterID;
    }

    public void setShippingCenterID(int shippingCenterID) {
        this.shippingCenterID = shippingCenterID;
    }

    public int getShippingSectionID() {
        return shippingSectionID;
    }

    public void setShippingSectionID(int shippingSectionID) {
        this.shippingSectionID = shippingSectionID;
    }

    public int getDeliveryTruckID() {
        return deliveryTruckID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getItem() {
        return item;
    }

    public void setDeliveryTruckID(int deliveryTruckID) {
        this.deliveryTruckID = deliveryTruckID;
    }

    public String getCategory() {
        return category;
    }

    public String[] orderAsArray(){
        return new String[]{ this.address, this.city, this.state, Integer.toString(this.zip), this.name, this.item, this.category};
    }
}
