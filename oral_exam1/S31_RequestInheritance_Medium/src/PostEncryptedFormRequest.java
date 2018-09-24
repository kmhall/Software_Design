import java.util.UUID;

public class PostEncryptedFormRequest extends PostFormRequest{
    private String encryptionScheme;
    private static int count;

    PostEncryptedFormRequest(UUID id, String ip, Form form,String encryptionScheme){
        super(id,ip,form);
        this.encryptionScheme = encryptionScheme;
        count++;
    }

    public String getEncryptionScheme() {
        return encryptionScheme;
    }

    @Override
    public String toString() {
        return super.toString() + "This form was encrypted using: " + getEncryptionScheme() + "\n";
    }

    public static int count(){
        return count;
    }
}
