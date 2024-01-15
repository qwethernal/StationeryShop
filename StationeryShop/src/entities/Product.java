package entities;

public class Product {
    private String name;
    private double price;
    private int quantitySold;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantitySold = 0;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void increaseQuantitySold(int quantity) {
        this.quantitySold += quantity;
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + ", quantitySold=" + quantitySold + '}';
    }
}
