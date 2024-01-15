package entities;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void addFunds(double amount) {
        this.balance += amount;
    }

    public void deductFunds(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "Customer{name='" + name + "', balance=" + balance + '}';
    }
}
