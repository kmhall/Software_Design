import java.util.UUID;

public class PostFormRequest extends PostRequest {

    private static  int count;
    private Form form;

    public PostFormRequest(UUID id, String ip,Form form){
        super(id,ip);
        this.form = form;
        count++;
    }

    private Form getForm() {
        return form;
    }
    public static int count(){return count;}

    @Override
    public String toString() {
        return super.toString()+"\n"+getForm().toString();
    }
}
