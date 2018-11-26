// Fig. 23.14: BlockingBuffer.java
// Creating a synchronized buffer using the ArrayBlockingQueue class.

import java.util.concurrent.ArrayBlockingQueue;

/**
 * BlockingBuffer is a class that sends AmazonOrders between two steps of the process
 * @see Buffer
 */
public class BlockingBuffer implements Buffer {
    private final ArrayBlockingQueue<AmazonOrder> buffer; // shared buffer

    /**
     * Constructor for a BlockingBuffer
     */
    public BlockingBuffer() {
        buffer = new ArrayBlockingQueue<AmazonOrder>(1);
    }

    /**
     * Places AmazonOrder into buffer
     * @param order AmazonOrder to put
     * @throws InterruptedException Exception
     */
    public void blockingPut(AmazonOrder order) throws InterruptedException {
        buffer.put(order); // place value in buffer
        //System.out.println("Blocking Put");
    }

    /**
     * Gets AmazonOrder from buffer
     * @return AmazonOrder
     * @throws InterruptedException Exception
     */
    public AmazonOrder blockingGet() throws InterruptedException {
        AmazonOrder readValue = buffer.take(); // remove value from buffer
        //System.out.println("Blocking Get");
        return readValue;
    }
}

