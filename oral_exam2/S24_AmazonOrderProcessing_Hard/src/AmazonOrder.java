public class AmazonOrder {

    private String address;
    private String city;
    private String state;
    private int zip;
    private String name;
    private String item;
    private String category;

    private Boolean terminatingKey;

    public AmazonOrder(String[] order){
        this.address = order[0];
        this.city = order[1];
        this.state = order[2];
        this.zip = Integer.parseInt(order[3]);
        this.name = order[4];
        this.item = order[5];
        this.category = order[6];

        this.terminatingKey = false;

    }

    @Override
    public String toString() {
        return super.toString() + "Address: " + address + " City: " + city + " State: " + state + " Zip:" + zip + " Name: " + name + " Item: "+ item + " Category: "+ category;
    }

    public void setTerminatingKey() {
        this.terminatingKey = true;
    }

    public Boolean getTerminatingKey() {
        return terminatingKey;
    }
}
