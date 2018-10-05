/**
 * Class that has functionality for a File
 */
public class File {

    private String filePath;
    private String fileType;
    private String url;


    /**
     * Constructor for a File
     * @param filePath String, path to a file
     * @param fileType String, what file type
     */
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

    /**
     *Converts file data to displayable data
     * @return String, displayable file
     */
    @Override
    public String toString() {
        String req = "Universal Resource Locator (URL): "+ getUrl()+
                "\nFile Path: "+ getFilePath()+"\nFile Type: "+getFileType();
        return req;
    }
}
