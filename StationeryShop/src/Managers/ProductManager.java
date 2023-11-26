package managers;

import entities.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ProductManager {
    private Map<String, Product> products;

    public ProductManager() {
        this.products = new HashMap<>();
    }

    public void addProduct(String name, double price) {
        products.put(name, new Product(name, price));
    }

    public int getProductSales(String productName) {
        Product product = products.get(productName);
        return product != null ? product.getQuantitySold() : -1;
    }

    public Product getProductByName(String productName) {
        for (Product product : products.values()) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null; // Если продукт не найден
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Product product : products.values()) {
            totalRevenue += (product.getPrice() * product.getQuantitySold());
        }
        return totalRevenue;
    }
}
