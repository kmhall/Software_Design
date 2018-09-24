import java.util.UUID;

public class PostEncryptedFormRequest extends PostFormRequest{

    private static int count;
    private String encryptionScheme;

    public PostEncryptedFormRequest(UUID id, String ip, Form form,String encryptionScheme){
        super(id,ip,form);
        this.encryptionScheme = encryptionScheme;
        count++;
    }

    private String getEncryptionScheme() {
        return encryptionScheme;
    }
    public static int count() {
        return count;
    }

    @Override
    public String toString() {
        return super.toString() + "\nThis form was encrypted using: " + getEncryptionScheme();
    }

}
