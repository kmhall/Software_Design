import java.util.UUID;

public class Request{

    private static int count = 0;

    private UUID requestUUID;

    public Request(UUID id){
        requestUUID = id;
        count ++;
    }

    @Override
    public String toString() {
        return "\nRequestInheritance."+super.toString() + "\n" + "UUID: " + requestUUID;

    }

    public static int count(){
        return count;
    }
}
