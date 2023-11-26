package StationeryShop;


import java.util.List;
import java.util.Scanner;
import entities.Product;
import entities.Customer;
import managers.ProductManager;
import managers.CustomerManager;

public class StationeryShop {
    private ProductManager productManager = new ProductManager();
    private CustomerManager customerManager = new CustomerManager();

    public static void main(String[] args) {
        StationeryShop shop = new StationeryShop();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Viborite optsiyu:");
            System.out.println("1. Dobavit' tovar");
            System.out.println("2. Dobavit' klienta");
            System.out.println("3. Prodat' tovar");
            System.out.println("4. Vivesti spisok tovarov");
            System.out.println("5. Vivesti spisok klientov");
            System.out.println("6. Popolnit' balans klienta");
            System.out.println("7. Vivesti obshchuyu vyru4ku");
            System.out.println("8. Polu4it' kolichestvo prodaj tovara");
            System.out.println("9. Polu4it' spisok klientov s opredelenim balansom");
            System.out.println("10. Vykhod");

            int choice = scanner.nextInt();
            scanner.nextLine(); // S4ityvaem simvol novoy stroki

            switch (choice) {
                case 1:
                    System.out.print("Vvedite nazvanie tovara: ");
                    String productName = scanner.nextLine();
                    System.out.print("Vvedite cenu tovara: ");
                    double price = scanner.nextDouble();
                    shop.productManager.addProduct(productName, price);
                    break;
                case 2:
                    System.out.print("Vvedite imya klienta: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Vvedite nachal'nyj balans: ");
                    double balance = scanner.nextDouble();
                    shop.customerManager.addCustomer(customerName, balance);
                    break;
                case 3:
                    System.out.print("Vvedite imya klienta: ");
                    String sellCustomerName = scanner.nextLine();
                    System.out.print("Vvedite nazvanie tovara: ");
                    String sellProductName = scanner.nextLine();
                    System.out.print("Vvedite kolichestvo: ");
                    int quantity = scanner.nextInt();

                    Product productToSell = shop.productManager.getProductByName(sellProductName);
                    Customer customerForSale = shop.customerManager.getCustomerByName(sellCustomerName);

                    if (productToSell != null && customerForSale != null) {
                        double totalPrice = productToSell.getPrice() * quantity;
                        if (customerForSale.getBalance() >= totalPrice) {
                            customerForSale.deductFunds(totalPrice);
                            productToSell.increaseQuantitySold(quantity);
                            System.out.println("Prodazha proshla uspeshno. Itogovaya summa: $" + totalPrice);
                        } else {
                            System.out.println("Nedostatochno sredstv dlya pokupki.");
                        }
                    } else {
                        System.out.println("Tovar ili klient ne nayden.");
                    }
                    break;

                case 4:
                    List<Product> productList = shop.productManager.getAllProducts();
                    if (!productList.isEmpty()) {
                        System.out.println("Spisok tovarov:");
                        for (Product prod : productList) {
                            System.out.println("Nazvanie: " + prod.getName() + ", Cena: $" + prod.getPrice());
                        }
                    } else {
                        System.out.println("Tovary ne naydeny.");
                    }
                    break;

                case 5:
                    List<Customer> customers = shop.customerManager.getAllCustomers();
                    if (!customers.isEmpty()) {
                        System.out.println("Spisok klientov:");
                        for (Customer customer : customers) {
                            System.out.println("Imya: " + customer.getName() + ", Balans: $" + customer.getBalance());
                        }
                    } else {
                        System.out.println("Klienty ne naydeny.");
                    }
                    break;

                case 6:
                    System.out.print("Vvedite imya klienta: ");
                    String name = scanner.nextLine();
                    System.out.print("Vvedite summu dlya popolneniya: ");
                    double amount = scanner.nextDouble();
                    shop.customerManager.addFundsToCustomer(name, amount);
                    break;
                case 7:
                    double totalRevenue = shop.productManager.calculateTotalRevenue();
                    System.out.println("Obshchaya vyru4ka: $" + totalRevenue);
                    break;

                case 8:
                    System.out.print("Vvedite nazvanie tovara dlya polucheniya kolichestva prodaj: ");
                    String prodName = scanner.nextLine();
                    int salesCount = shop.productManager.getProductSales(prodName);
                    if (salesCount >= 0) {
                        System.out.println("Kolichestvo prodaj dlya " + prodName + ": " + salesCount);
                    } else {
                        System.out.println("Tovar ne nayden.");
                    }
                    break;
                case 9:
                    System.out.print("Vvedite summu balansa: ");
                    double minAmount = scanner.nextDouble();
                    List<Customer> highSpendingCustomers = shop.customerManager.getCustomersWithHighPurchase(minAmount);
                    if (!highSpendingCustomers.isEmpty()) {
                        System.out.println("Klienty,balans kotorix ili bolshe $" + minAmount + ":");
                        for (Customer customer : highSpendingCustomers) {
                            System.out.println(customer.getName() + " - Balans: $" + customer.getBalance());
                        }
                    } else {
                        System.out.println("Klienty s balansom bolee $" + minAmount + " ne naydeny");
                    }
                    break;
                case 10:
                    running = false;
                    break;
                default:
                    System.out.println("Nevernyj vybor. Poprobujte eshche raz.");
            }
        }
        scanner.close();
        System.out.println("Zavershenie programmy.");
    }
}
