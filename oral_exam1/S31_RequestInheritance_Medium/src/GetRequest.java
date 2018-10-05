import java.util.UUID;

/**
 * Class GetRequest extends Request
 * @see Request
 */
public class GetRequest extends Request {

    private static int count;
    private String url;

    //Base Constructor

    /**
     * Base GetRequest Constructor
     * @param id UUID, get request id
     * @param url String, URL of what to get
     */
    public GetRequest(UUID id,String url){
        super(id);
        this.url = url;
        count++;
    }

    /**
     *Special Case Constructor: for when the GetRequest is being constructed with a child URL
     * @param id UUID, get request id
     */
    public GetRequest(UUID id){
        super(id);
        this.url = null;
        count++;
    }

    private String getUrl() {
        return url;
    }
    public static int count() {
        return count;
    }

    /**
     * Converts GetRequest to displayable data
     * @return String, displayable GetRequest data
     */
    @Override
    public String toString() {
        if(getUrl() == null) {
            return super.toString();
        }
        return super.toString()+"\nUniversal Resource Locator (URL): "+getUrl() ;
    }

}
