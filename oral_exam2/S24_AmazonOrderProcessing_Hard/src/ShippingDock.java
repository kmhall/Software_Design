/**
 * ShippingDock is a class that represents a shipping dock in an amazon order process.
 * @see Runnable
 */
public class ShippingDock implements Runnable {

    private final BlockingBuffer sectionToShippingDock; // reference to shared object
    private final BlockingBuffer docktoDeliveryTruck1;
    private final BlockingBuffer dock1ToDeliveryTruck2;
    private final int dock;

    /**
     * Constructs a ShippingDock
     * @param sectionToShippingDock BlockingBuffer from section to the dock
     * @param docktoDeliveryTruck1 BlockingBuffer from dock to truck
     * @param dock1ToDeliveryTruck2 BlockingBuffer from dock to truck
     * @param dock Current dock ID
     */
    public ShippingDock(BlockingBuffer sectionToShippingDock,BlockingBuffer docktoDeliveryTruck1,BlockingBuffer dock1ToDeliveryTruck2,int dock) {
        this.sectionToShippingDock = sectionToShippingDock;
        this.docktoDeliveryTruck1 = docktoDeliveryTruck1;
        this.dock1ToDeliveryTruck2 = dock1ToDeliveryTruck2;

        this.dock = dock;
    }

    /**
     * Sends an AmazonOrder to the appropriate BlockingBuffer
     * @param buffer BlockingBuffer to send to
     * @param order AmazonOrder to send
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
     * Gets an amazon orders from the two sections and sends orders to the appropriate truck. Fills truck 1 first
     * and then fills truck 2. If both trucks fill, the first will begin to be filled again.
     */
    public synchronized void run() {
        AmazonOrder currentOrder = null;
        int fillTruckA = 0;
        int fillTruckB = 0;


        do {
            try {

                    currentOrder = sectionToShippingDock.blockingGet();
                if(currentOrder.getTerminatingKey() != true) {

                    if (fillTruckA <= 3) {
                        sendOrder(docktoDeliveryTruck1, currentOrder);
                        fillTruckA++;
                        fillTruckB = 0;
                    } else if (fillTruckB <= 3) {
                        sendOrder(dock1ToDeliveryTruck2, currentOrder);
                        fillTruckB++;
                        if (fillTruckB / 4 == 1) {
                            fillTruckA = 0;
                        }
                    }
                }
                else{
                    sendOrder(docktoDeliveryTruck1, currentOrder);
                    sendOrder(dock1ToDeliveryTruck2, currentOrder);
                }

                //System.out.println("***  Dock " + dock+ " Center "+ currentOrder.getShippingCenterID()+ " Section "+ currentOrder.getShippingSectionID()+" | "+currentOrder.getShippingSectionID());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        //System.out.println("\nDock "+dock+" terminating\n");
    }
}
