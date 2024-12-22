
public class Discount {
    private String promoCode;
    private double discountPercent;

    public Discount(String promoCode, double discountPercent) {
        this.promoCode = promoCode;
        this.discountPercent = discountPercent;
    }

    public double applyDiscount(double amount) {
        return amount - (amount * (discountPercent / 100));
    }

    @Override
    public String toString() {
        return "Discount[ Promotion: " + promoCode + ", Discount Percent: " + discountPercent + "]";
    }
}
