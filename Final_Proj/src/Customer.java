
public class Customer {
    private String name;
    private String email;
    private String address;

    public Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer[Name:" + name + ", Email: " + email + ", Address: " + address + "]";
    }
}
