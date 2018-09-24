import java.util.UUID;

public class PostPaymentRequest extends PostRequest{
    private static int count =0;
    private Payment payment;

    PostPaymentRequest(UUID id, String ip,Payment payment){
        super(id,ip);
        this.payment = payment;
        count++;

    }

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n"+getPayment().toString();
    }

    public static int count() {
        return count;
    }

}
