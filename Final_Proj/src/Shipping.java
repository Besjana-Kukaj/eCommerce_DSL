
public class Shipping {
    private Order order;
    private String shippingMethod;

    public Shipping(Order order, String shippingMethod) {
        this.order = order;
        this.shippingMethod = shippingMethod;
    }

    public void ship() {
        System.out.println("Shipping order " + order + " via " + shippingMethod);
    }
}
