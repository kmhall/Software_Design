import java.util.UUID;

public class GetRequest extends Request {

    private static int count;
    private String url;

    //Base Constructor
    public GetRequest(UUID id,String url){
        super(id);
        this.url = url;
        count++;
    }

    //Special Case Constructor: for when the GetRequest is being constructed with a child URL
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

    @Override
    public String toString() {
        if(getUrl() == null) {
            return super.toString();
        }
        return super.toString()+"\nUniversal Resource Locator (URL): "+getUrl() ;
    }

}
