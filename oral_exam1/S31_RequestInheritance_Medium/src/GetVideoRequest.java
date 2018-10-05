import java.util.UUID;

/**
 * GetVideoRequest extends GetRequest. This is a specific type of get request
 * @see GetRequest
 */
public class GetVideoRequest extends GetRequest{

    private static int count;
    private Video video;

    /**
     * Constructor of GetVideoRequest
     * @param id UUID, id of GetVideoRequest
     * @param video Video, video to get
     */
    public GetVideoRequest(UUID id, Video video) {
            super(id);
            this.video = video;
            count++;
    }

    /**
     * gets the video requested
     * @return Video, video requested
     */
    private Video getVideo() {
            return video;
    }
    /**
     * Static counter to increment calls fo this class
     * @return int, count
     */
    public static int count(){return count;}

    /**
     * Converts GetVideoRequest to displayable data
     * @return String, displayable GetVideoRequest data
     */
    @Override
    public String toString() {
        return super.toString() + "\n" + getVideo().toString();
    }
}


