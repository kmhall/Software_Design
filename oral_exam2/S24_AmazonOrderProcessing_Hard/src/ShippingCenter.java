/**
 * ShippingCenter is a class that represents a shipping center in an Amazon ordering process.
 * @see Runnable
 */
public class ShippingCenter implements Runnable {
    private final BlockingBuffer webServerToShippingCenter1; // reference to shared object
    private final BlockingBuffer shippingCenterToSection1; // reference to shared object
    private final BlockingBuffer shippingCenterToSection2; // reference to shared object

    private final int center;

    /**
     * Constucts a ShippingCenter.
     * @param webServerToShippingCenter1 BlockingBuffer web server to shipping center
     * @param shippingCenterToSection1 BlockingBuffer to section 1
     * @param shippingCenterToSection2 BlockingBuffer to section 2
     * @param center Center id
     */
    public ShippingCenter(BlockingBuffer webServerToShippingCenter1, BlockingBuffer shippingCenterToSection1, BlockingBuffer shippingCenterToSection2,int center) {
        this.webServerToShippingCenter1 = webServerToShippingCenter1;
        this.shippingCenterToSection1 = shippingCenterToSection1;
        this.shippingCenterToSection2 = shippingCenterToSection2;
        this.center = center;
    }

    /**
     * Sends an order to the corresponding BlockingBuffer.
     * @param buffer BlockingBuffer to send to
     * @param order Amazon Order to send
     */
    public void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     *Gets an order from the WebServer and sends order to the correct category of the shipping center.
     * Shipping center categories are broken up alphabetically.
     */
    public void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                currentOrder = webServerToShippingCenter1.blockingGet();
                currentOrder.setShippingCenterID(center);


                if(currentOrder.getTerminatingKey() != true) {
                    //System.out.println("*    Center " + currentOrder.getShippingCenterID() + "| " + currentOrder.getCity());


                    if (!startsWithAtoP(currentOrder.getCategory())) {
                        sendOrder(shippingCenterToSection2, currentOrder);
                    } else {
                        sendOrder(shippingCenterToSection1, currentOrder);
                    }
                }
                else{
                        //Send terminating key
                        sendOrder(shippingCenterToSection2,currentOrder);
                        sendOrder(shippingCenterToSection1,currentOrder);
                    }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        //System.out.println("\nCenter "+ center +" terminating\n");
    }

    /**
     * Checks for what letter the category starts with
     * @param category String Category to check
     * @return Boolean true if startsWithAtoP
     */
    public boolean startsWithAtoP(String category){
        String [] splitCategory = category.split("");

        return splitCategory[0].toUpperCase().matches("[A-P]");
    }

}
