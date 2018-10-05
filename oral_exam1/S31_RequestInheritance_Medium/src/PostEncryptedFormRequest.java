import java.util.UUID;
/**
 * PostEncryptedFormRequest extends PostFormRequest. This is a specific type of post request.
 * @see PostFormRequest
 */
public class PostEncryptedFormRequest extends PostFormRequest{

    private static int count;
    private String encryptionScheme;

    /**
     * Constructor for PostEncryptedFormRequest
     * @param id UUID, PostEncryptedFormRequest id
     * @param ip String, ip address for post
     * @param form Form, form to be posted
     * @param encryptionScheme String, what type of encryption necessary
     */
    public PostEncryptedFormRequest(UUID id, String ip, Form form,String encryptionScheme){
        super(id,ip,form);
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
    public static int count() {
        return count;
    }

    /**
     * Converts PostEncryptedFormRequest to displayable data
     * @return String, displayable PostEncryptedFormRequest data
     */
    @Override
    public String toString() {
        return super.toString() + "\nThis form was encrypted using: " + getEncryptionScheme();
    }

}
