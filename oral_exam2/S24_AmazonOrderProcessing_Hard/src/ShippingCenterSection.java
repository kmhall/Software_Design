import java.security.SecureRandom;

public class ShippingCenterSection implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final BlockingBuffer shippingCenterToSection; // reference to shared object
    private final int center;
    private final int section;

    // constructor
    public ShippingCenterSection(BlockingBuffer shippingCenter1ToSection, int center, int section) {
        this.shippingCenterToSection = shippingCenter1ToSection;
        this.center = center;
        this.section = section;
    }

    // read sharedLocation's value 10 times and sum the values
    public void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                currentOrder = shippingCenterToSection.blockingGet();
                currentOrder.setShippingSectionID(section);

                Thread.sleep(generator.nextInt(1000));

                System.out.println("** Center "+ currentOrder.getShippingCenterID()+ " Section"+ currentOrder.getShippingSectionID()+" | "+currentOrder.getCategory());

            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("\nCenter "+center+" section "+section +" terminating\n");
    }
}

