package org.example;

import java.util.ArrayList;

/**
 * This class is a proxy for the Database class.
 * It is used to hide the Database class from the other classes.
 * This is done to make the code more maintainable.
 */

public class DatabaseProxy {

    private Database database;

    public DatabaseProxy() { this.database = new Database(); }

    public ArrayList<Product> getProducts() { return this.database.getProducts(); }

    public ArrayList<Customer> getCustomers() { return this.database.getCustomers(); }

}
