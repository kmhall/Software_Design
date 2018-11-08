
public class ShippingDock implements Runnable {

    private final BlockingBuffer sectionToShippingDock; // reference to shared object
    private final BlockingBuffer docktoDeliveryTruck1;
    private final BlockingBuffer dock1ToDeliveryTruck2;
    private final int dock;

    // constructor
    public ShippingDock(BlockingBuffer sectionToShippingDock,BlockingBuffer docktoDeliveryTruck1,BlockingBuffer dock1ToDeliveryTruck2,int dock) {
        this.sectionToShippingDock = sectionToShippingDock;
        this.docktoDeliveryTruck1 = docktoDeliveryTruck1;
        this.dock1ToDeliveryTruck2 = dock1ToDeliveryTruck2;

        this.dock = dock;
    }

    public void sendOrder(BlockingBuffer buffer,AmazonOrder order){
        try
        {
            buffer.blockingPut(order); // set value in buffer
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }

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

//                System.out.println("***  Dock " + dock+ " Center "+ currentOrder.getShippingCenterID()+ " Section"+ currentOrder.getShippingSectionID()+" | "+currentOrder.getShippingSectionID());
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        while(currentOrder.getTerminatingKey() != true);

        System.out.println("\nDock "+dock+" terminating\n");
    }
}
