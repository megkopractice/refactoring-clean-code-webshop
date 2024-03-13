package org.example;

import java.util.ArrayList;

/**
 * This class represents a customer.
 *
 * (Joshua Bloch's version of) Builder Pattern is used to create a customer
 * https://blogs.oracle.com/javamagazine/post/exploring-joshua-blochs-builder-design-pattern-in-java
 * @author Megumi Kogo
 */
public class Customer {
    // Fields
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String address;
    private String phoneNumber;
    private int funds;
    private ArrayList<Order> orders;

    // Constructor using Builder Pattern (sets values through a builder)
    private Customer(Builder builder)
    {
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.age = builder.age;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
        this.orders = builder.orders;
        this.funds = builder.funds;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getFunds() {
        return funds;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * This inner class represents a builder for a customer.
     *
     * It contains methods for setting the values of the customer's fields.
     * It also contains a build method for creating a customer.
     */
    public static class Builder
    {
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private String email;
        private int age;
        private String address;
        private String phoneNumber;
        private int funds;
        private ArrayList<Order> orders;

        // Constructor
        public Builder(String username)
        {
            this.username = username;
            this.orders = new ArrayList<>();
            this.funds = 100; // bonus funds for new customers
        }

        // Setters
        public Builder password(String password)
        {
            this.password = password;
            return this;
        }

        public Builder firstName(String firstName)
        {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName)
        {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email)
        {
            this.email = email;
            return this;
        }

        public Builder age(int age)
        {
            this.age = age;
            return this;
        }

        public Builder address(String address)
        {
            this.address = address;
            return this;
        }

        public Builder phoneNumber(String phoneNumber)
        {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder funds(int funds)
        {
            this.funds = funds;
            return this;
        }

        public Builder orders(ArrayList<Order> orders)
        {
            this.orders = orders;
            return this;
        }

        public Customer build()
        {
            return new Customer(this);
        }
    }

    // Methods related to customer
    public void addFunds(int amount) {
        funds += amount;
    }
    public void removeFunds(int amount) {
        funds -= amount;
    }

    public boolean CanAfford(int price)
    {
        return funds >= price;
    }

    public boolean CheckPassword(String password)
    {
        if (password == null)
        {
            return true;
        }
        return password.equals(this.password);
    }

    public void PrintCustomerInfo() // changed method name from PrintInfo() to PrintCustomerInfo()
    {
        System.out.println();
        System.out.print("Username: " + username + "");
        if (password != null)
        {
            System.out.print(", Password: " + password);
        }
        if (firstName != null)
        {
            System.out.print(", First Name: " + firstName);
        }
        if (lastName != null)
        {
            System.out.print(", Last Name: " + lastName);
        }
        if (email != null)
        {
            System.out.print(", Email: " + email);
        }
        if (age != -1)
        {
            System.out.print(", Age: " + age);
        }
        if (address != null)
        {
            System.out.print(", Address: " + address);
        }
        if (phoneNumber != null)
        {
            System.out.print(", Phone Number: " + phoneNumber);
        }
        System.out.println(", Funds: " + funds);
        System.out.println();
    }

    public void PrintOrderHistory() // changed method name from PrintInfo() to PrintOrderHistory()
    {
        System.out.println();
        for (Order order : orders)
        {
            order.PrintOrderInfo();
        }
        System.out.println();
    }
}
