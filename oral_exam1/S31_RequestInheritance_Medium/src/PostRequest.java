import java.util.UUID;

public class PostRequest extends Request{

    private static int count;
    private String ip;

    public PostRequest(UUID id, String ip){
        super(id);
        this.ip = ip;
        count++;
    }

    private String getIp() {
        return ip;
    }
    public static int count(){return count;}

    @Override
    public String toString() {
        return super.toString()+"\nPost request to server with IP address: "+getIp() ;
    }
}
