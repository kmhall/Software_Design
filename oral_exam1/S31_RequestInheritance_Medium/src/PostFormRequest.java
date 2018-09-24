import java.util.UUID;

public class PostFormRequest extends PostRequest {

    private Form form;
    private static  int count;

    public PostFormRequest(UUID id, String ip,Form form){
        super(id,ip);
        this.form = form;
        count++;
    }

    public Form getForm() {
        return form;
    }

    @Override
    public String toString() {
        return super.toString()+"\n"+getForm().toString();
    }

    public static int count(){return count;}



}
