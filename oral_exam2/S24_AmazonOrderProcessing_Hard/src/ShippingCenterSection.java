import java.security.SecureRandom;

/**
 * ShippingCenterSection is a class that represents the Section of a Shipping Center in an Amazon order process.
 * @see Runnable
 */
public class ShippingCenterSection implements Runnable {

    private static final SecureRandom generator = new SecureRandom();

    private final BlockingBuffer shippingCenterToSection; // reference to shared object
    private final BlockingBuffer sectionToShippingDock;

    private final int center;
    private final int section;

    /**
     * Constructs a ShippingCenterSection
     * @param shippingCenterToSection BlockingBuffer from shippingCenter to Section
     * @param sectionToShippingDock BlockingBuffer from section to dock
     * @param center int center id
     * @param section int section id
     */
    public ShippingCenterSection(BlockingBuffer shippingCenterToSection,BlockingBuffer sectionToShippingDock, int center, int section) {
        this.shippingCenterToSection = shippingCenterToSection;
        this.sectionToShippingDock = sectionToShippingDock;
        this.center = center;
        this.section = section;
    }

    /**
     * Sends Amazon order to the correct BlockingBuffer synchronously.
     * @param buffer Blocking buffer to send
     * @param order AmazonOrder to send
     */
    public synchronized void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * Gets amazon order from the shipping center and sends it to the dock.
     * This is done synchronously due to multiple sections accessing the same buffer.
     */
    public synchronized void run() {
        AmazonOrder currentOrder = null;

        do {
            try {
                currentOrder = shippingCenterToSection.blockingGet();
                currentOrder.setShippingSectionID(section);

                Thread.sleep(generator.nextInt(5000));

                if(currentOrder.getTerminatingKey() != true) {

                    //System.out.println("**   Center "+ currentOrder.getShippingCenterID()+ " Section"+ currentOrder.getShippingSectionID()+" | "+currentOrder.getCategory());
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

        //System.out.println("\nCenter "+center+" section "+section +" terminating\n");
    }
}

