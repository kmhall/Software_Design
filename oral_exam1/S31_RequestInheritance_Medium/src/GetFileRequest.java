import java.util.UUID;

public class GetFileRequest extends GetRequest {
    private static int count = 0;
    private File file;
    public GetFileRequest(UUID id, File file){
        super(id);
        this.file = file;
        count++;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+getFile().toString();
    }

    public static int count(){return count;}
}
