public class Payment {

    private String sender;
    private int amount;
    private String receiver;

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

    @Override
    public String toString() {
        String req = "Payment Data\nPayment sender: " + getSender() +
                "\nPayment amount: $" + getAmount() + "\nPayment receiver: " +
                getReceiver();
        return req;
    }
}
