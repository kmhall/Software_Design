import java.util.UUID;

/**
 * Class Request has the basic functionality of a Request
 */
public class Request{

    private static int count;
    private UUID requestUUID;

    /**
     * Constructor of Request
     * @param id UUID, id of request
     */
    public Request(UUID id){
        requestUUID = id;
        count ++;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count(){
        return count;
    }
    /**
     * Converts Request to displayable data
     * @return String, displayable Request data
     */
    @Override
    public String toString() {
        return "\nRequestInheritance."+super.toString() + "\n" + "UUID: " + requestUUID;
    }
}
