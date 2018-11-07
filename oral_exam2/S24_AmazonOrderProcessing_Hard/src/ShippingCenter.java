import java.security.SecureRandom;

public class ShippingCenter implements Runnable {
    private static final SecureRandom generator = new SecureRandom();
    private final Buffer sharedLocation; // reference to shared object

    // constructor
    public ShippingCenter(Buffer sharedLocation) {
        this.sharedLocation = sharedLocation;
    }

    // read sharedLocation's value 10 times and sum the values
    public void run() {
        AmazonOrder currentOrder =null;

        do {
            try {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                Thread.sleep(generator.nextInt(500));
                currentOrder = sharedLocation.blockingGet();
                System.out.println(currentOrder);
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("Shipping Center Terminating");
    }

}
