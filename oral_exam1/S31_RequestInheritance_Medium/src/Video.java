public class Video {

    private String url;
    private String title;
    private String name;

    public Video(String url,String title,String name){
        setUrl(url);
        setTitle(title);
        setName(name);
    }

    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+
                getUrl()+"\nVideo: "+ getTitle()+"\nBy: "+
                getName();
        return req;
    }
}

