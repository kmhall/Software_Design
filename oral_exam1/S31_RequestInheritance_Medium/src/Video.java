public class Video {

    private String url;
    private String title;
    private String name;

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

    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+
                getUrl()+"\nVideo: "+ getTitle()+"\nBy: "+
                getName();
        return req;
    }
}

