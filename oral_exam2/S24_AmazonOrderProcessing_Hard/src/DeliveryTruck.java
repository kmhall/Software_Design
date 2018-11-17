import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

/**
 * DeliveryTruck is a class that represents a Delivery Truck in an order process.
 * @see Runnable
 */
public class DeliveryTruck implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final BlockingBuffer shippingDockToTruck; // reference to shared object
    private final int truck;
    private final int center;

    private final Queue<AmazonOrder> queue;

    /**
     * Constructs a DeliveryTruck
     * @param shippingDockToTruck BlockingBuffer from a shippingDock
     * @param truck int truck id
     * @param center int center id
     */
    public DeliveryTruck(BlockingBuffer shippingDockToTruck,int truck,int center) {

        this.shippingDockToTruck = shippingDockToTruck;
        this.truck = truck;
        this.center = center;
        this.queue = new LinkedList();;
    }


    /**
     * Fill truck to capacity of 4 and then print the contents of the truck to the screen.
     */
    public void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                currentOrder = shippingDockToTruck.blockingGet();
                currentOrder.setDeliveryTruckID(truck);

                if(currentOrder.getTerminatingKey() != true) {
                    queue.add(currentOrder);
                }

                if(queue.size() == 4 || currentOrder.getTerminatingKey() == true){
                    Thread.sleep(generator.nextInt(10000));

                    while (!queue.isEmpty()){
                    //System.out.println("**** Truck "+truck);

                        AmazonOrder outputOrder = queue.remove();
                        System.out.println(outputOrder.getAddress() +" | "+ outputOrder.getName() +" | "+ outputOrder.getItem() +" | "+outputOrder.getCategory()+" | "+outputOrder.getShippingCenterID()+" | "+outputOrder.getShippingSectionID()+" | "+outputOrder.getDeliveryTruckID());
                    }
                }
                if(currentOrder.getTerminatingKey() == true){
                    System.out.println("**** No More Orders: Truck "+truck+" | Shipping Center "+center );
                }

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        //System.out.println("\nTruck "+truck+" terminating\n");
    }
}
