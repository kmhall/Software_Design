import java.util.UUID;

public class GetVideoRequest extends GetRequest{

    private static int count;
    private Video video;

    public GetVideoRequest(UUID id, Video video) {
            super(id);
            this.video = video;
            count++;
    }

    private Video getVideo() {
            return video;
    }
    public static int count(){return count;}

    @Override
    public String toString() {
        return super.toString() + "\n" + getVideo().toString();
    }
}


