/**
 * // Buffer interface.
 */
public interface Buffer {
    /**
     * Place AmazonOrder into Buffer
     *
     * @param value AmazonOrder
     * @throws InterruptedException Exception
     */
     void blockingPut(AmazonOrder value) throws InterruptedException;


    /**
     * Obtain AmazonOrder from Buffer
     * @return AmazonOrder
     * @throws InterruptedException Exception
     */
     AmazonOrder blockingGet() throws InterruptedException;
}

