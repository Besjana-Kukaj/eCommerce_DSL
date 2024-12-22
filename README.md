This DSL is simply the skeleton of what is needed to run a eCommerece site, but with a working DBMS linked- it could help smoothly keep track and do many of the things needed for a eCommerece site. 



The domain I’ve chosen for my DSL is running an eCommerce site. In today's fast-paced digital world, eCommerce platforms are integral to most retail businesses. Managing a product catalog, customer profiles, orders, payments, and shipments forms the backbone of any eCommerce operation. 
A specialized DSL can make these tasks much simpler, reducing the amount of boilerplate code developers need to write and helping them focus on the core functionality of the platform.
Here are a few common tasks in eCommerce systems where a DSL can prove to be more useful than a GL!   

Managing Customer Profiles: This task involves creating, updating, or deleting customer information, such as name, email, and shipping address.

Order Management: The ability to place orders, update their statuses, and track shipments is central to the smooth functioning of any online store.
Inventory Management: This involves adding new products, updating their details (price, stock quantity), and removing outdated or out-of-stock items.

 **Abstractions my DSL will Support**
 
The core idea behind the DSL is to provide intuitive abstractions for common tasks in eCommerce. These abstractions will be highly focused on simplifying the developer's workflow, removing the need to write repetitive code for basic operations.

createProduct: Adds new products to the catalog.
This abstraction makes it easy to add products without dealing with database CRUD operations directly, making product management faster and more intuitive.

createCustomer: Manages customer profiles.
Handling customer data is crucial for any eCommerce platform, and this abstraction allows easy creation of customer profiles, reducing the chance for errors.

createOrder: Ties together customer and product details into an order.
Orders are the heart of an eCommerce system. This abstraction simplifies order creation, ensuring that the correct customer and product are associated with each transaction.

applyDiscount: Applies promotional discounts to orders.
Discounts are a frequent part of eCommerce, and this abstraction makes it easy to implement discount codes or promotional pricing without cluttering the logic.
processPayment: Handles payment transactions for orders.
 Payment processing is a complex task. By isolating it in its own abstraction, we ensure the core system remains clean and adaptable to different payment systems.

shipOrder: Manages the shipping process, including selecting shipping methods and tracking deliveries.
 Shipping is a critical part of eCommerce. This abstraction makes it easy to manage different shipping options and keep the customer informed about the status of their orders.

completeOrder: Marks the order as complete and triggers any follow-up actions, such as sending confirmation emails or updating inventory.
 This abstraction helps close the loop on each order, ensuring that the final steps of order completion happen smoothly.

Detailed Design for Abstractions
The three most important abstractions, in my opinion, for this DSL I’ve designed: createProduct, createCustomer, and createOrder. These abstractions are at the core of any eCommerce operation and help make the tasks of managing products, customers, and orders seamless and straightforward and the other abstractions can’t process without these abstractions. 



1. Detailed Design for createProduct
This abstraction enables the creation of new products in the system by accepting the product's name, price, and stock quantity as arguments. The product is then added to the product database, which is stored in a HashMap.


Parameters:
String name: The name of the product.
double price: The product's price.
int stockQuantity: The quantity of the product in stock.



Steps:
The method extracts the parameters from the provided input string.
It creates a new Product object using these details.
The product is stored in the productCatalog for future retrieval.
A success message is printed to confirm that the product has been added.


Code Example (Java):
private static void createProduct(String args) {
    String[] parts = args.split(", ");
    String productName = parts[0];
    double price = Double.parseDouble(parts[1]);
    int stockQuantity = Integer.parseInt(parts[2]);
    Product product = new Product(productName, price, stockQuantity);
    productCatalog.put(productName, product);
    System.out.println("Product created: " + product);
}

2. Detailed Design for createCustomer
The createCustomer abstraction handles customer data. It requires the customer’s name, email, and shipping address.
This abstraction is key to ensuring customer data is consistent, retrievable, and exists when trying to place an order or any other task the DSL can do. 

Parameters:
String name: The customer’s name.
String email: The customer’s email address.
String address: The customer’s shipping address.

Steps:
The method extracts customer details from the provided input string.
It creates a Customer object using these details.
The customer is stored in the customerDatabase (a HashMap for fast look-up).
A confirmation message is printed, verifying the customer was created successfully.

Code Example:
private static void createCustomer(String args) {
    String[] parts = args.split(", ");
    String name = parts[0];
    String email = parts[1];
    String address = parts[2];
    Customer customer = new Customer(name, email, address);
    customerDatabase.put(name, customer);
    System.out.println("Customer created: " + customer);
}

3. Detailed Design for createOrder
This abstraction ties together customers and products by creating an order. It requires a unique order ID, the customer’s name, the product name, and the quantity of the product being purchased.

Parameters:
int orderId: A unique identifier for the order.
String customerName: The name of the customer placing the order.
String productName: The name of the product being purchased.
int quantity: The quantity of the product being purchased.

Steps:
The method splits the input into individual components.
It retrieves the corresponding Customer and Product objects from their respective databases.
If both exist, it creates an Order object and stores it in the orderDatabase.
A confirmation message is printed, verifying the order was created successfully.

Code Example:
private static void createCustomer(String args) {
		String[] parts = args.split(", ");
		String name = parts[0];
		String email = parts[1];
		String address = parts[2];
		Customer customer = new Customer(name, email, address);
		customerDatabase.put(name, customer);
		System.out.println("Customer created: " + customer);
	}

4. Integration of Code with the Report: Sample Execution
To demonstrate the capabilities of the eCommerce DSL, here’s how it works in practice. The following snippet represents how the code interacts with the file that contains DSL commands.

public static void main(String[] args) {
     parseDSL("DSL.txt");
}

In this example, the parseDSL() method reads the DSL.txt file line by line. It then splits the commands and their arguments, passing them to the corresponding methods.
Here’s an example of what the contents of DSL.txt might look like:

createProduct: Smartphone, 499.99, 50
createCustomer: Alice, alice@example.com, 123 Oak St
createOrder: 1001, Alice, Smartphone, 2
applyDiscount: SUMMER20, 20
processPayment: 799.98
shipOrder: 1001, Express
completeOrder: 1001


When the parseDSL() function reads this file, it processes each line, calling the relevant methods (createProduct, createCustomer, createOrder, etc.). Each method then manipulates the internal data structures (productCatalog, customerDatabase, orderDatabase) and 
prints out the result.


The output should look like:

Product created: Product[name=Smartphone, price=499.99, stockQuantity=50]
Customer created: Customer[name=Alice, email=alice@example.com, address=123 Oak St]
Order created: Order[orderId=1001, customer=Customer[name=Alice, email=alice@example.com, address=123 Oak St], product=Product[name=Smartphone, price=499.99, stockQuantity=50], quantity=2, status=Pending]
Applied discount SUMMER20 of 20.0%
Processed payment of $799.98
Shipping order Order[orderId=1001, customer=Customer[name=Alice, email=alice@example.com, address=123 Oak St], product=Product[name=Smartphone, price=499.99, stockQuantity=50], quantity=2, status=Pending] via Express
Order 1001 completed.

This is in the case that all tasks and abstractions where processed successfully, but in the case of missing foreign key references or other errors, this will be displayed in the output as some form of “not found” referring to the 
missing/incorrect information. That output might look like:

Product created: Product[name=Smartphone, price=499.99, stockQuantity=50]
Customer created: Customer[name=Alice, email=alice@example.com, address=123 Oak St]
Error creating order. Customer or Product not found.
Applied discount SUMMER20 of 20.0%
Processed payment of $799.98
Order not found.
Order not found.
This is because the customer name was altered in the order placed, therefore there was no customer for the order to reference and the order was never placed.
