public class File {

    private String filePath;
    private String fileType;
    private String url;


    public File(String filePath, String fileType){
        this.filePath = filePath;
        this.fileType = fileType;
        this.url = "file://" + filePath + "." + fileType;
    }

    private String getUrl() {
        return url;
    }

    private String getFilePath() {
        return filePath;
    }

    private String getFileType() {
        return fileType;
    }

    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+ getUrl()+
                "\nFile Path: "+ getFilePath()+"\nFile Type: "+getFileType();
        return req;
    }
}
