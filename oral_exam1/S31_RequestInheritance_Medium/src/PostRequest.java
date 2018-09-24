import java.util.UUID;

public class PostRequest extends Request{
    private static int count = 0;

    private String ip;

    public PostRequest(UUID id, String ip){
        super(id);
        this.ip = ip;
        count++;
    }

    public String getIp() {
        return ip;
    }

    @Override
    public String toString() {
        return super.toString()+"\nPost request to server with IP address: "+getIp() ;
    }

    public static int count(){return count;}

}
