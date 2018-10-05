import java.util.UUID;

/**
 * PostEncryptedPaymentRequest extends PostPaymentRequest. This is a specific type of post request.
 * @see PostPaymentRequest
 */
public class PostEncryptedPaymentRequest extends  PostPaymentRequest{

    private static int count;
    private String encryptionScheme;

    /**
     * Constructor for PostEncryptedPaymentRequest
     * @param id UUID, id of PostEncryptedPaymentRequest
     * @param ip String, ip of PostEncryptedPaymentRequest
     * @param payment Payment, payment that is going to be posted
     * @param encryptionScheme String, what encryption scheme to use
     */
    public PostEncryptedPaymentRequest(UUID id,String ip, Payment payment, String encryptionScheme){
        super(id,ip,payment);
        this.encryptionScheme = encryptionScheme;
        count++;
    }

    private String getEncryptionScheme() {
        return encryptionScheme;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count(){return count;}

    /**
     * Converts PostEncryptedPaymentRequest to displayable data
     * @return String, displayable PostEncryptedPaymentRequest data
     */
    @Override
    public String toString() {
        return super.toString()+"\nThis form was encrypted using: "+getEncryptionScheme();
    }
}

