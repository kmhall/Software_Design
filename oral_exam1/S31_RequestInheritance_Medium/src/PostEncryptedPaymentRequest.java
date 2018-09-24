import java.util.UUID;

public class PostEncryptedPaymentRequest extends  PostPaymentRequest{

    private static int count;
    private String encryptionScheme;

    public PostEncryptedPaymentRequest(UUID id,String ip, Payment payment, String encryptionScheme){
        super(id,ip,payment);
        this.encryptionScheme = encryptionScheme;
        count++;
    }

    private String getEncryptionScheme() {
        return encryptionScheme;
    }
    public static int count(){return count;}

    @Override
    public String toString() {
        return super.toString()+"\nThis form was encrypted using: "+getEncryptionScheme();
    }
}

