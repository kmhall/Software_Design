import java.util.UUID;

public class Request{

    private static int count;
    private UUID requestUUID;

    public Request(UUID id){
        requestUUID = id;
        count ++;
    }

    public static int count(){
        return count;
    }

    @Override
    public String toString() {
        return "\nRequestInheritance."+super.toString() + "\n" + "UUID: " + requestUUID;
    }
}
