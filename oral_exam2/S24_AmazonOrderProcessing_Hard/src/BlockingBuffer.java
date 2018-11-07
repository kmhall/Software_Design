// Fig. 23.14: BlockingBuffer.java
// Creating a synchronized buffer using the ArrayBlockingQueue class.

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {
    private final ArrayBlockingQueue<AmazonOrder> buffer; // shared buffer

    public BlockingBuffer() {
        buffer = new ArrayBlockingQueue<>(1);
    }

    // place value into buffer
    public void blockingPut(AmazonOrder order) throws InterruptedException {
        buffer.put(order); // place value in buffer
//        System.out.println("Blocking Put");
    }

    // return value from buffer
    public AmazonOrder blockingGet() throws InterruptedException {
        AmazonOrder readValue = buffer.take(); // remove value from buffer
//        System.out.println("Blocking Get");

        return readValue;
    }
} // end class BlockingBuffer

