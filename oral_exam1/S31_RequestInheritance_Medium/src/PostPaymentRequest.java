import java.util.UUID;

/**
 * PostPaymentRequest extends PostRequest. This is a specific type of post request.
 */
public class PostPaymentRequest extends PostRequest{

    private static int count;
    private Payment payment;

    /**
     * Constructor for PostPaymentRequest
     * @param id UUID, id of PostPaymentRequest
     * @param ip String, ip address of request
     * @param payment Payment, payment tob be posted
     */
    public PostPaymentRequest(UUID id, String ip,Payment payment){
        super(id,ip);
        this.payment = payment;
        count++;

    }

    private Payment getPayment() {
        return payment;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count() {
        return count;
    }

    /**
     * Converts PostPaymentRequest to displayable data
     * @return String, displayable PostPaymentRequest data
     */
    @Override
    public String toString() {
        return super.toString()+ "\n"+getPayment().toString();
    }
}
