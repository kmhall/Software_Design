import java.util.UUID;

public class PostPaymentRequest extends PostRequest{

    private static int count;
    private Payment payment;

    public PostPaymentRequest(UUID id, String ip,Payment payment){
        super(id,ip);
        this.payment = payment;
        count++;

    }

    private Payment getPayment() {
        return payment;
    }
    public static int count() {
        return count;
    }

    @Override
    public String toString() {
        return super.toString()+ "\n"+getPayment().toString();
    }
}
