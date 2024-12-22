import java.io.*;
import java.util.*;

public class ECommereceDSL {

	// Store products, customers, and orders for simplicity
	static Map<String, Product> productCatalog = new HashMap<>();
	static Map<String, Customer> customerDatabase = new HashMap<>();
	static Map<Integer, Order> orderDatabase = new HashMap<>();

	// Parse DSL commands from the file
	public static void parseDSL(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				processCommand(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Process each DSL command
	private static void processCommand(String commandLine) {
		String[] parts = commandLine.split(": ");
		String command = parts[0].trim();
		String arguments = parts[1].trim();

		switch (command) {
		case "createProduct":
			createProduct(arguments);
			break;
		case "createCustomer":
			createCustomer(arguments);
			break;
		case "createOrder":
			createOrder(arguments);
			break;
		case "applyDiscount":
			applyDiscount(arguments);
			break;
		case "processPayment":
			processPayment(Double.parseDouble(arguments));
			break;
		case "shipOrder":
			shipOrder(arguments);
			break;
		case "completeOrder":
			completeOrder(Integer.parseInt(arguments));
			break;
		default:
			System.out.println("Unknown command: " + command);
		}
	}

	// Create Product
	private static void createProduct(String args) {
		String[] parts = args.split(", ");
		String productName = parts[0];
		double price = Double.parseDouble(parts[1]);
		int stockQuantity = Integer.parseInt(parts[2]);
		Product product = new Product(productName, price, stockQuantity);
		productCatalog.put(productName, product);
		System.out.println("Product created: " + product);
	}

	// Create Customer
	private static void createCustomer(String args) {
		String[] parts = args.split(", ");
		String name = parts[0];
		String email = parts[1];
		String address = parts[2];
		Customer customer = new Customer(name, email, address);
		customerDatabase.put(name, customer);
		System.out.println("Customer created: " + customer);
	}

	// Create Order
	private static void createOrder(String args) {
		String[] parts = args.split(", ");
		int orderId = Integer.parseInt(parts[0]);
		String customerName = parts[1];
		String productName = parts[2];
		int quantity = Integer.parseInt(parts[3]);

		Customer customer = customerDatabase.get(customerName);
		Product product = productCatalog.get(productName);

		if (customer != null && product != null) {
			Order order = new Order(orderId, customer, product, quantity);
			orderDatabase.put(orderId, order);
			System.out.println("Order created: " + order);
		} else {
			System.out.println("Error creating order. Customer or Product not found.");
		}
	}

	// Apply Discount
	private static void applyDiscount(String args) {
		String[] parts = args.split(", ");
		String promoCode = parts[0];
		double discount = Double.parseDouble(parts[1]);
		System.out.println("Applied discount: " + promoCode + " of " + discount + "%");
	}

	// Process Payment
	private static void processPayment(double amount) {
		System.out.println("Processed payment of $" + amount);
	}

	// Ship Order
	private static void shipOrder(String args) {
		// Split the string based on comma and space
		String[] parts = args.split(",\\s*");

		// Check if there are exactly two parts
		if (parts.length == 2) {
			try {
				// Parse the order ID as an integer
				int orderId = Integer.parseInt(parts[0].trim()); // Trim to avoid any leading/trailing spaces
				String shippingMethod = parts[1].trim(); // The second part is the shipping method (string)

				// Retrieve the order from the orderDatabase
				Order order = orderDatabase.get(orderId);
				if (order != null) {
					// Create a new Shipping object and ship the order
					Shipping shipping = new Shipping(order, shippingMethod);
					shipping.ship();
				} else {
					System.out.println("Order not found.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid order ID format. It should be a number.");
			}
		} else {
			System.out.println("Invalid input format. Expected format: 'orderId, shippingMethod'.");
		}
	}

	// Complete Order
	private static void completeOrder(int orderId) {
		Order order = orderDatabase.get(orderId);
		if (order != null) {
			order.completeOrder();
		} else {
			System.out.println("Order not found.");
		}
	}

	public static void main(String[] args) {

		parseDSL("DSL.txt");
		
	}
}
