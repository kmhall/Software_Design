import java.util.UUID;

public class PostEncryptedPaymentRequest extends  PostPaymentRequest{

    private String encryptionScheme;
    private static int count = 0;
    PostEncryptedPaymentRequest(UUID id,String ip, Payment payment, String encryptionScheme){
        super(id,ip,payment);
        this.encryptionScheme = encryptionScheme;
        count++;
    }

    public String getEncryptionScheme() {
        return encryptionScheme;
    }

    public static int count(){return count;}

    @Override
    public String toString() {
        return super.toString()+"\nThis form was encrypted using: "+getEncryptionScheme();
    }
}

