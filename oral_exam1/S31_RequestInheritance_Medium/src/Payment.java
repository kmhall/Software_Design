/**
 * Class that has functionality for a Payment
 */
public class Payment {

    private String sender;
    private int amount;
    private String receiver;

    /**
     * Constructor of a Payment
     * @param sender String, who payment is from
     * @param amount int, amount for payment
     * @param receiver String, who payment is for
     */
    public Payment(String sender, int amount, String receiver) {
        this.sender = sender;
        this.amount = amount;
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public int getAmount() {
        return amount;
    }
    public String getReceiver() {
        return receiver;
    }

    /**
     *Converts Payment data to displayable data
     * @return String, displayable payment
     */
    @Override
    public String toString() {
        String req = "Payment Data\nPayment sender: " + getSender() +
                "\nPayment amount: $" + getAmount() + "\nPayment receiver: " +
                getReceiver();
        return req;
    }
}
