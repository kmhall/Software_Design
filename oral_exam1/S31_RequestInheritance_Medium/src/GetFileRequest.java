import java.util.UUID;

public class GetFileRequest extends GetRequest {

    private static int count;
    private File file;

    public GetFileRequest(UUID id, File file) {
        super(id);
        this.file = file;
        count++;
    }

    public static int count() {
        return count;
    }

    private File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + getFile().toString();
    }
}
