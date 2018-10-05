import java.util.UUID;

/**
 * Class GetFileRequest is an extension fo the class GetRequest
 * @see GetRequest
 */
public class GetFileRequest extends GetRequest {

    private static int count;
    private File file;

    /**
     * Constructor for GetFileRequest
     * @param id UUID, id of file request
     * @param file File, file to get
     */
    public GetFileRequest(UUID id, File file) {
        super(id);
        this.file = file;
        count++;
    }

    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count() {
        return count;
    }

    /**
     * Gets the file
     * @return File, file that was retrieved
     */
    private File getFile() {
        return file;
    }

    /**
     * Converts GetFileRequest to displayable data
     * @return String, displayable GetFileRequest data
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + getFile().toString();
    }
}
