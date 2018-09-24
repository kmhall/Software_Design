public class File {

    private String url;
    private String filePath;
    private String fileType;

    public File(String filePath, String fileType){
        setFilePath(filePath);
        setFileType(fileType);
        setUrl("file://"+filePath+"."+fileType);
    }

    public String getUrl() {
        return url;
    }
    public String getFilePath() {
        return filePath;
    }
    public String getFileType() {
        return fileType;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+ getUrl()+
                "\nFile Path: "+ getFilePath()+"\nFile Type: "+getFileType();
        return req;
    }
}
