import java.util.UUID;

/**
 * PostRequest extends Request. This is a specific type of post request.
 */
public class PostRequest extends Request{

    private static int count;
    private String ip;

    /**
     * Constructor of PostRequest
     * @param id UUID, id of post request
     * @param ip
     */
    public PostRequest(UUID id, String ip){
        super(id);
        this.ip = ip;
        count++;
    }

    private String getIp() {
        return ip;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count(){return count;}
    /**
     * Converts PostRequest to displayable data
     * @return String, displayable PostRequest data
     */
    @Override
    public String toString() {
        return super.toString()+"\nPost request to server with IP address: "+getIp() ;
    }
}
