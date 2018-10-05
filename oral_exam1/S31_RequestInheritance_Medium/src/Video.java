/**
 * Class that has functionality for a Video
 */
public class Video {

    private String url;
    private String title;
    private String name;

    /**
     * Constructor for a video
     * @param url String, url where the video exists
     * @param title String, title of video
     * @param name String, name of creator
     */
    public Video(String url,String title,String name){
        this.url = url;
        this.title = title;
        this.name = name;
    }

    private String getUrl() {
        return url;
    }
    private String getTitle() {
        return title;
    }
    private String getName() {
        return name;
    }

    /**
     * Converts Video to displayable data
     * @return String, displayable Video data
     */
    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+
                getUrl()+"\nVideo: "+ getTitle()+"\nBy: "+
                getName();
        return req;
    }
}

