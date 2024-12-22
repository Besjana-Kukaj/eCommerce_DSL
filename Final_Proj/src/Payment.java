
public class Payment {
    private Order order;
    private double amount;

    public Payment(Order order, double amount) {
        this.order = order;
        this.amount = amount;
    }

    public void processPayment() {
        System.out.println("Payment of $" + amount + " processed for order " + order);
    }
}
