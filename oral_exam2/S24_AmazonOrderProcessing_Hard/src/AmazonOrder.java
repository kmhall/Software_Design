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

    public AmazonOrder(String[] order){
        this.address = order[0];
        this.city = order[1];
        this.state = order[2];
        this.zip = Integer.parseInt(order[3]);
        this.name = order[4];
        this.item = order[5];
        this.category = order[6];

        this.shippingCenterID = 0;
        this.shippingSectionID = 0;
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

    public String getCategory() {
        return category;
    }
}
