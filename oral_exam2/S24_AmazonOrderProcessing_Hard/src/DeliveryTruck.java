import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

public class DeliveryTruck implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final BlockingBuffer shippingDockToTruck; // reference to shared object
    private final int truck;

    private final Queue<AmazonOrder> queue;

    // constructor
    public DeliveryTruck(BlockingBuffer shippingDockToTruck,int truck) {

        this.shippingDockToTruck = shippingDockToTruck;
        this.truck = truck;
        this.queue = new LinkedList();;
    }



    // read sharedLocation's value 10 times and sum the values
    public void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                currentOrder = shippingDockToTruck.blockingGet();
                currentOrder.setDeliveryTruckID(truck);

                if(currentOrder.getTerminatingKey() != true) {
                    queue.add(currentOrder);
                }

                if(queue.size() == 4 || currentOrder.getTerminatingKey() == true){
                    Thread.sleep(generator.nextInt(10000));

                    while (!queue.isEmpty()){
//                        System.out.println("**** Truck "+truck);

                        AmazonOrder outputOrder = queue.remove();
                        System.out.println(outputOrder.getAddress() +" | "+ outputOrder.getName() +" | "+ outputOrder.getItem() +" | "+outputOrder.getCategory()+" | "+outputOrder.getShippingCenterID()+" | "+outputOrder.getShippingSectionID()+" | "+outputOrder.getDeliveryTruckID());
                    }
                }

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("\nTruck "+truck+" terminating\n");
    }
}
