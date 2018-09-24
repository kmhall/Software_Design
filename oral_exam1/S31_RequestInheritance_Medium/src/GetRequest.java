import java.util.UUID;

public class GetRequest extends Request {

    private static int count = 0;

    private String url;
    public GetRequest(UUID id,String url){
        super(id);
        this.url = url;
        count++;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return super.toString()+"\nUniversal Resource Locator (URL): "+getUrl() ;
    }

    public static int count(){return count;}
}
