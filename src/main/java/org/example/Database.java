package org.example;

import java.util.ArrayList;

/**
 * This class is an imitation of a real database *
 */

public class Database {
    // We just pretend this accesses a real database.
    private ArrayList<Product> productsInDatabase;
    private ArrayList<Customer> customersInDatabase;
    public Database()
    {
        productsInDatabase = new ArrayList<Product>();
        productsInDatabase.add(new Product("Shirt", 100, 2));
        productsInDatabase.add(new Product("Mirror", 300, 2));
        productsInDatabase.add(new Product("Coffee", 30, 2));
        productsInDatabase.add(new Product("Pen", 50, 2));
        productsInDatabase.add(new Product("Book", 200, 2));
        productsInDatabase.add(new Product("iPhone 15 Pro", 13500, 2));
        productsInDatabase.add(new Product("Apple Vision Pro", 50000, 2));
        productsInDatabase.add(new Product("Laptop", 15000, 2));
        productsInDatabase.add(new Product("Game", 600, 2));
        productsInDatabase.add(new Product("Painting", 400, 2));
        productsInDatabase.add(new Product("Chair", 500, 2));
        productsInDatabase.add(new Product("Table", 1000, 2));
        productsInDatabase.add(new Product("Bed", 20000, 2));

        customersInDatabase = new ArrayList<Customer>();
        // We use the Builder pattern to create the Customer objects.
        customersInDatabase.add(new Customer.Builder("jimmy")
                                        .password("jimisthebest")
                                        .firstName("Jimmy")
                                        .lastName("Jamesson")
                                        .email("jj@mail.com")
                                        .age(22).address("Big Street 5")
                                        .phoneNumber("123456789")
                                        .build());

        // We use the Builder pattern to create the Customer objects.
        customersInDatabase.add(new Customer.Builder("jake")
                                        .password("jake123")
                                        .firstName("Jake")
                                        .age(0)
                                        .build());
    }

    public ArrayList<Product> getProducts()
    {
        return productsInDatabase;
    }

    public ArrayList<Customer> getCustomers()
    {
        return customersInDatabase;
    }
}
