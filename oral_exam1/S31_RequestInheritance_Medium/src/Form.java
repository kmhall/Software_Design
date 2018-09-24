import java.util.HashMap;

public class Form {

    private String favColor;
    private String encryptionScheme;
    private String name;

    public Form(HashMap<String, String> fields){
        setFavColor(fields.get("Favorite color"));
        setEncryptionScheme(fields.get("Favorite encryption scheme"));
        setName(fields.get("Name"));
    }

    private String getFavColor(){
        return  favColor;
    }
    private String getEncryptionScheme(){return encryptionScheme;}
    private String getName(){return  name;}

    private void setFavColor(String favColor){
        this.favColor = favColor;
    }
    private void setEncryptionScheme(String encryptionScheme){this.encryptionScheme = encryptionScheme;}
    private void setName(String name){this.name = name;}



    @Override
    public String toString(){
        String req = "Form Data\nLabel: Favorite color\nValue: "+getFavColor()+
                "\nLabel: Favorite encryption scheme\nValue: " +getEncryptionScheme()+
                "\nLabel: Name\nValue: "+ getName();
    return req;
    }
}
