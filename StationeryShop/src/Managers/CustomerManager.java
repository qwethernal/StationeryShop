package managers;

import entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;

    public CustomerManager() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String name, double initialBalance) {
        customers.add(new Customer(name, initialBalance));
    }

    public void addFundsToCustomer(String customerName, double amount) {
        Customer customer = getCustomerByName(customerName);
        if (customer != null) {
            customer.addFunds(amount);
            System.out.println(amount + " added to " + customerName + "'s account. New balance: $" + customer.getBalance());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public List<Customer> getCustomersWithHighPurchase(double amount) {
        List<Customer> highSpendingCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getBalance() >= amount) {
                highSpendingCustomers.add(customer);
            }
        }
        return highSpendingCustomers;
    }

    public Customer getCustomerByName(String customerName) {
        for (Customer customer : customers) {
            if (customer.getName().equals(customerName)) {
                return customer;
            }
        }
        return null;
    }
    
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}
