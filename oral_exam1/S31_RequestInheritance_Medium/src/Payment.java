public class Payment {
    private String sender;
    private int amount;
    private String receiver;

    Payment(String sender,int amount,String reciever){
        this.sender = sender;
        this.amount = amount;
        this.receiver = reciever;
    }

    public String getSender() {
        return sender;
    }
    public String getReceiver() {
        return receiver;
    }
    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        String req = "Payment Data\nPayment sender: "+getSender()+
                "\nPayment amount: "+ getAmount()+"\nPayment receiver: "+
                getReceiver();
        return req;
    }
}
