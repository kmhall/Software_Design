import java.util.UUID;
/**
 * PostFormRequest extends PostRequest. This is a specific type of post request.
 * @see PostRequest
 */
public class PostFormRequest extends PostRequest {

    private static  int count;
    private Form form;

    /**
     * Constructor for PostFormRequest
     * @param id
     * @param ip
     * @param form
     */
    public PostFormRequest(UUID id, String ip,Form form){
        super(id,ip);
        this.form = form;
        count++;
    }

    private Form getForm() {
        return form;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count(){return count;}

    /**
     * Converts PostFormRequest to displayable data
     * @return String, displayable PostFormRequest data
     */
    @Override
    public String toString() {
        return super.toString()+"\n"+getForm().toString();
    }
}
