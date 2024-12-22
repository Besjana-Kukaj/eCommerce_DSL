
public class Order {
    private int orderId;
    private Customer customer;
    private Product product;
    private int quantity;
    private String status;

    public Order(int orderId, Customer customer, Product product, int quantity) {
        this.orderId = orderId;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.status = "Pending";
    }

    public void processOrder() {
        this.status = "Processed";
        product.updateStock(-quantity); // Reduce stock
        System.out.println("Order " + orderId + " processed.");
    }

    public void shipOrder() {
        this.status = "Shipped";
        System.out.println("Order " + orderId + " shipped.");
    }

    public void completeOrder() {
        this.status = "Completed";
        System.out.println("Order " + orderId + " completed.");
    }

    @Override
    public String toString() {
        return "Order[Order ID:" + orderId + ", Customer: " + customer + ", Product: " + product + ", Quantity: " + quantity + ", Status: " + status + "]";
    }
}
