package StationeryShop;
        
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import entities.Product;
import entities.Customer;
import managers.ProductManager;
import managers.CustomerManager;

public class StationeryShop {
    private ProductManager productManager = new ProductManager();
    private CustomerManager customerManager = new CustomerManager();
    private static final String FILE_PATH = "data.txt"; 

    public static void main(String[] args) {
        StationeryShop shop = new StationeryShop();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a product");
            System.out.println("2. Add a customer");
            System.out.println("3. Sell a product");
            System.out.println("4. Display the list of products");
            System.out.println("5. Display the list of customers");
            System.out.println("6. Replenish customer balance");
            System.out.println("7. Display total revenue");
            System.out.println("8. Get the quantity of product sales");
            System.out.println("9. Get the list of customers with a certain balance");
            System.out.println("10. Exit");
            System.out.println("11. Save data to a files");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter the product name: ");
                    String productName = scanner.nextLine();
                    System.out.print("Enter the product price: ");
                    double price = scanner.nextDouble();
                    shop.productManager.addProduct(productName, price);
                    break;
                case 2:
                    System.out.print("Enter the customer name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter the initial balance: ");
                    double balance = scanner.nextDouble();
                    shop.customerManager.addCustomer(customerName, balance);
                    break;
                case 3:
                    System.out.print("Enter the customer name: ");
                    String sellCustomerName = scanner.nextLine();
                    System.out.print("Enter the product name: ");
                    String sellProductName = scanner.nextLine();
                    System.out.print("Enter the quantity: ");
                    int quantity = scanner.nextInt();

                    Product productToSell = shop.productManager.getProductByName(sellProductName);
                    Customer customerForSale = shop.customerManager.getCustomerByName(sellCustomerName);

                    if (productToSell != null && customerForSale != null) {
                        double totalPrice = productToSell.getPrice() * quantity;
                        if (customerForSale.getBalance() >= totalPrice) {
                            customerForSale.deductFunds(totalPrice);
                            productToSell.increaseQuantitySold(quantity);
                            System.out.println("Sale successful. Total amount: $" + totalPrice);
                        } else {
                            System.out.println("Insufficient funds for the purchase.");
                        }
                    } else {
                        System.out.println("Product or customer not found.");
                    }
                    break;

                case 4:
                    List<Product> productList = shop.productManager.getAllProducts();
                    if (!productList.isEmpty()) {
                        System.out.println("List of products:");
                        for (Product prod : productList) {
                            System.out.println("Name: " + prod.getName() + ", Price: $" + prod.getPrice());
                        }
                    } else {
                        System.out.println("Products not found.");
                    }
                    break;

                case 5:
                    List<Customer> customers = shop.customerManager.getAllCustomers();
                    if (!customers.isEmpty()) {
                        System.out.println("List of customers:");
                        for (Customer customer : customers) {
                            System.out.println("Name: " + customer.getName() + ", Balance: $" + customer.getBalance());
                        }
                    } else {
                        System.out.println("Customers not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter the customer name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the amount for replenishment: ");
                    double amount = scanner.nextDouble();
                    shop.customerManager.addFundsToCustomer(name, amount);
                    break;
                case 7:
                    double totalRevenue = shop.productManager.calculateTotalRevenue();
                    System.out.println("Total revenue: $" + totalRevenue);
                    break;

                case 8:
                    System.out.print("Enter the product name to get the quantity of sales: ");
                    String prodName = scanner.nextLine();
                    int salesCount = shop.productManager.getProductSales(prodName);
                    if (salesCount >= 0) {
                        System.out.println("Quantity of sales for " + prodName + ": " + salesCount);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 9:
                    System.out.print("Enter the balance amount: ");
                    double minAmount = scanner.nextDouble();
                    List<Customer> highSpendingCustomers = shop.customerManager.getCustomersWithHighPurchase(minAmount);
                    if (!highSpendingCustomers.isEmpty()) {
                        System.out.println("Customers with a balance of $" + minAmount + " or more:");
                        for (Customer customer : highSpendingCustomers) {
                            System.out.println(customer.getName() + " - Balance: $" + customer.getBalance());
                        }
                    } else {
                        System.out.println("Customers with a balance of more than $" + minAmount + " not found");
                    }
                    break;
                case 10:
                    running = false;
                    break;
                case 11:
                    writeDataToFile(shop.productManager.getAllProducts(), "products.txt");
                    writeDataToFile(shop.customerManager.getAllCustomers(), "customers.txt");
                    System.out.println("Data is saved to txt.");
                    break;
            }
        }
        scanner.close();
        System.out.println("App ending.");
    }

    private static void writeDataToFile(List<? extends Object> dataList, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Object data : dataList) {
                writer.write(data.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}