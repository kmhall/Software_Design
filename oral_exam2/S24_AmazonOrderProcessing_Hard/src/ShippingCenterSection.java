import java.security.SecureRandom;

public class ShippingCenterSection implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final BlockingBuffer shippingCenterToSection; // reference to shared object
    private final BlockingBuffer sectionToShippingDock;

    private final int center;
    private final int section;

    // constructor
    public ShippingCenterSection(BlockingBuffer shippingCenter1ToSection,BlockingBuffer sectionToShippingDock, int center, int section) {
        this.shippingCenterToSection = shippingCenter1ToSection;
        this.sectionToShippingDock = sectionToShippingDock;
        this.center = center;
        this.section = section;
    }

    public synchronized void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }

    // read sharedLocation's value 10 times and sum the values
    public synchronized void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                // sleep 0 to 3 seconds, read value from buffer and add to sum
                currentOrder = shippingCenterToSection.blockingGet();
                currentOrder.setShippingSectionID(section);

                Thread.sleep(generator.nextInt(5000));

                if(currentOrder.getTerminatingKey() != true) {

//                System.out.println("**   Center "+ currentOrder.getShippingCenterID()+ " Section"+ currentOrder.getShippingSectionID()+" | "+currentOrder.getCategory());
                    sendOrder(sectionToShippingDock, currentOrder);

                }
                else if(section == 1){
                    Thread.sleep(generator.nextInt(6000));
                    sendOrder(sectionToShippingDock, currentOrder);
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }

        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("\nCenter "+center+" section "+section +" terminating\n");
    }
}

