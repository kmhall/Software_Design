import java.util.HashMap;

public class Form {

    private String favColor;
    private String encryptionScheme;
    private String name;

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

    @Override
    public String toString(){
        String req = "Form Data\nLabel: Favorite color\nValue: "+getFavColor()+
                "\nLabel: Favorite encryption scheme\nValue: " +getEncryptionScheme()+
                "\nLabel: Name\nValue: "+ getName();
    return req;
    }
}
