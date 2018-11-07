
public class ShippingCenter implements Runnable {
    private final BlockingBuffer webServerToShippingCenter1; // reference to shared object
    private final BlockingBuffer shippingCenterToSection1; // reference to shared object
    private final BlockingBuffer shippingCenterToSection2; // reference to shared object

    private final int center;

    // constructor
    public ShippingCenter(BlockingBuffer webServerToShippingCenter1, BlockingBuffer shippingCenterToSection1, BlockingBuffer shippingCenterToSection2,int center) {
        this.webServerToShippingCenter1 = webServerToShippingCenter1;
        this.shippingCenterToSection1 = shippingCenterToSection1;
        this.shippingCenterToSection2 = shippingCenterToSection2;
        this.center = center;
    }

    public void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }

    // read sharedLocation's value 10 times and sum the values
    public void run() {
        AmazonOrder currentOrder =null;

        do {
            try {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                currentOrder = webServerToShippingCenter1.blockingGet();
                currentOrder.setShippingCenterID(center);

                System.out.println("*  Center "+ currentOrder.getShippingCenterID()+ "| "+currentOrder.getCity());

                if(startsWithAtoP(currentOrder.getCategory())){
                    sendOrder(shippingCenterToSection1,currentOrder);
                }
                else{
                    sendOrder(shippingCenterToSection2,currentOrder);

                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("\nCenter "+ center +" terminating\n");
    }

    public boolean startsWithAtoP(String category){
        String [] splitCategory = category.split("");

        return splitCategory[0].toUpperCase().matches("[A-P]");
    }

}
