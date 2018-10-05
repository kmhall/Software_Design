import java.util.HashMap;

/**
 * Class that has functionality for a Form
 */
public class Form {

    private String favColor;
    private String encryptionScheme;
    private String name;

    /**
     * Constructor for a Form
     * @param fields HashMap<String,String>, Key value pairs for form inputs.
     */
    public Form(HashMap<String, String> fields){
        this.favColor = fields.get("Favorite color");
        this.encryptionScheme = fields.get("Favorite encryption scheme");
        this.name = fields.get("Name");
    }

    private String getFavColor(){
        return  favColor;
    }
    private String getEncryptionScheme(){return encryptionScheme;}
    private String getName(){return  name;}

    /**
     *Converts form data to displayable data
     * @return String, displayable form
     */
    @Override
    public String toString(){
        String req = "Form Data\nLabel: Favorite color\nValue: "+getFavColor()+
                "\nLabel: Favorite encryption scheme\nValue: " +getEncryptionScheme()+
                "\nLabel: Name\nValue: "+ getName();
    return req;
    }
}
