package org.example;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The WebShop class is the main class of the program.
 * Refactoring a web shop program with Design Patterns (clean code)
 * *
 * Design Patterns and Algorithms used in this program are as follows:
 * 1. Builder Pattern on Customer class ☆ OK
 * (Customer object is created in the Database class using Builder pattern)
 * 2. Proxy Pattern on DatabaseProxy class　☆ OK
 * 3. Command Pattern on WebShop class (No Switch statements or else-if statements). ☆ OK
 *    - Command interface, -CommandHandler class
 *    - Command classes: all kinds of commands (e.g. SortMenuDisplayCommand, RegisterActionCommand)　
 * 4. Merge Sort algorithm on mergeSort method in WebShop class ☆ OK
 *
 * Info: There seemed to be some logical errors or typos here and there in the original code which took me a while to figure out.
 * One on them is that purchase can be interchanged with wares menu, so I replaced purchase with wares menu.
 * @author Megumi Kogo
 */

public class WebShop {
    boolean running = true;
    //Database database = new Database();
    DatabaseProxy databaseProxy = new DatabaseProxy();
    ArrayList<Product> products;
    ArrayList<Customer> customers;


    String currentMenu = "main menu";

    //int currentChoice = 1;
    //int amountOfOptions = 3;
    String option1 = "See Wares";
    String option2 = "Customer Info";
    String option3 = "Login";
    String option4 = "";
    String info = "What would you like to do? Type in the button name.";

    //String username = null;
    String password = null;
    Customer currentCustomer;

    Scanner scanner = new Scanner(System.in);

    public Map<Button, Command> commands = new HashMap<>();

    public WebShop() {
        products = databaseProxy.getProducts();
        customers = databaseProxy.getCustomers();

        // Back, Quit Button

        Button backToMain = new Button("back");
        commands.put(backToMain, new BackToMainMenuNavigationCommand(this));
        Button quit = new Button("quit"); // ok
        commands.put(quit, new QuitButtonCommand(this)); // ok

        // Buttons for the main menu
        Button defaultMenu = new Button("default menu"); // ok
        commands.put(defaultMenu, new DefaultUIDisplayCommand(this)); // ok
        Button mainMenu = new Button("main menu"); // ok
        commands.put(mainMenu, new BackToMainMenuNavigationCommand(this)); // ok
        Button seeWears = new Button("see wares");
        commands.put(seeWears, new SeeWaresNavigationCommand(this));// ok
        Button waresMenu = new Button("wares menu");
        commands.put(waresMenu, new SeeWaresNavigationCommand(this));// ok
        Button customerInfo = new Button("customer info");
        commands.put(customerInfo, new CustomerInfoNavigationCommand(this));// ok
        Button loginMenu = new Button("login menu");
        commands.put(loginMenu, new LoginMenuDisplayCommand(this));// ok

        // Buttons for the wares menu
        Button seeAllWares = new Button("see all wares");
        commands.put(seeAllWares, new SeeAllWaresActionCommand(this));// ok
        Button purchaseWare = new Button("purchase a ware");
        commands.put(purchaseWare, new PurchaseAWareActionCommand(this));// ok
        Button sortWares = new Button("sort wares");
        commands.put(sortWares, new SortMenuDisplayCommand(this)); // ok. This should display sort wares

        // Buttons for the sort wares menu (wares menu -> sort wares
        Button sortByNameDescending = new Button("sort by name, descending");
        commands.put(sortByNameDescending, new SortByNameDescendingCommand(this)); // ok
        Button sortByNameAscending = new Button("sort by name, ascending");
        commands.put(sortByNameAscending, new SortByNameAscendingCommand(this)); // ok
        Button sortByPriceDescending = new Button("sort by price, descending");
        commands.put(sortByPriceDescending, new SortByPriceDescendingCommand(this)); // ok
        Button sortByPriceAscending = new Button("sort by price, ascending");
        commands.put(sortByPriceAscending, new SortByPriceAscendingCommand(this)); // ok

        // Buttons for the customer menu
        Button seeOrders = new Button("see your orders");
        commands.put(seeOrders, new SeeYourOrdersActionCommand(this)); // ok
        Button seeInfo = new Button("see your info"); // set your info is a typo again. It should be "see your info"
        commands.put(seeInfo, new SeeYourInfoActionCommand(this)); // ok
        Button addFunds = new Button("add funds");
        commands.put(addFunds, new AddFundsActionCommand(this)); // ok

        // Buttons for the login menu
        Button setUsername = new Button("set username");
        commands.put(setUsername, new SetUsernameActionCommand(this));// ok
        Button setPassword = new Button("set password");
        commands.put(setPassword, new SetPasswordActionCommand(this));// ok
        Button login = new Button("login");
        commands.put(login, new LoginActionCommand(this)); // ok
        Button register = new Button("register");
        commands.put(register, new RegisterActionCommand(this)); // ok

    }

    /**
     * The displayDefaultMenu method displays the menu and handles the user's input.
     * It is the main method of the program.
     * It is refactored from the Run method.
     * It is refactored to use the Command pattern.
     */
    public void displayDefaultMenu() {
        System.out.println("Welcome to the WebShop!");

        boolean handleOptionExecuted = false;

        while (running) {
            if(!handleOptionExecuted){
                for(Button button: commands.keySet()){
                    if(button.getName().equals(currentMenu)){
                        commands.get(button).execute();
                    }
                }
            }

            System.out.println(info);
            displayButtonInstructions();
            displayUserStatus();

            String choice = scanner.nextLine().trim().toLowerCase();
            handleOption(choice);
            handleOptionExecuted = true;
        }
    }

    /**
     * The handleOption method finds the selected button and executes the corresponding command.
     * It is called by the displayDefaultMenu method.
     * It is refactored to use the Command pattern.
     */
    private void handleOption(String choice) {
        Button selectedButton = null;
        for (Button button : commands.keySet()) {
            if (button.getName().equalsIgnoreCase(choice)) {
                selectedButton = button;
                break;
            }
        }

        if (selectedButton != null) {
            Command command = commands.get(selectedButton);
            command.execute();

        } else {
            System.out.println("Invalid option. Choose from the available options.");
        }
    }

    // Refactored the Run method to displayMainMenu method
    public void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1: " + "See Wares");
        System.out.println("2: " + "Customer Info");
        System.out.println("3: " + "Login Menu");
    }

    // Refactored the Run method to displayButtonInstructions method
    private void displayButtonInstructions() {
        System.out.println("Your buttons are : Main Menu | [Option Name] | Back | Quit");
    }

    // Refactored the Run method to displayUserStatus method
    private void displayUserStatus() {
        if (currentCustomer != null) {
            System.out.println("Current user: " + currentCustomer.getUsername());
        } else {
            System.out.println("You are not logged in.");
        }
    }

    /**
     * The register method registers a new customer.
     * The Builder pattern is used to create a new customer.
     */
    public void register() {
        // Check if the username is already taken
        System.out.println("REGISTRATION");
        System.out.println("Please write your username.");
        String newUsername = scanner.nextLine();

        if(isUsernameTaken(newUsername)) {
            System.out.println("Username already taken. Please choose another one.");
            return;
        }
        System.out.println("Please write your password.");
        String passwordReg  = scanner.nextLine();
        System.out.println("Please write your first name.");
        String firstNameReg = scanner.nextLine();
        System.out.println("Please write your last name.");
        String lastNameReg = scanner.nextLine();
        System.out.println("Please write your email.");
        String emailReg = scanner.nextLine();
        System.out.println("Please write your age.");
        int ageReg = Integer.parseInt(scanner.nextLine());
        System.out.println("Please write your address.");
        String addressReg = scanner.nextLine();
        System.out.println("Please write your phone number.");
        String phoneNumberReg = scanner.nextLine();

        // Create a new customer using the Builder pattern
        Customer newCustomer = new Customer.Builder(newUsername)
                .password(passwordReg)
                .firstName(firstNameReg)
                .lastName(lastNameReg)
                .email(emailReg)
                .age(ageReg)
                .address(addressReg)
                .phoneNumber(phoneNumberReg)
                .build();

        // Add the new customer to the list of customers
        customers.add(newCustomer);

        System.out.println("Registration successful!");
    }

    // Helper method to check if a username is already taken
    private boolean isUsernameTaken(String username) {
        for (Customer customer : customers) {
            if(customer.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The login method logs in a customer and logs out the current customer if there is one.
     *
     */
    public void login() {
        if (currentCustomer == null) {
            // User is not logged in, prompt for login
            System.out.println("USER LOGIN");
            System.out.println("Please type in your username.");
            String usernameInput = scanner.nextLine();
            Customer foundCustomer = findCustomerByUsername(usernameInput);
            // Check if the username exists
            if(foundCustomer == null) {
                System.out.println("Username not found. Please register if you are a new user.");
                return;
            }
            System.out.println("Please type in your password.");
            String passwordInput = scanner.nextLine();
            // Check if the password matches
            if(!foundCustomer.CheckPassword(passwordInput)) {
                System.out.println("Incorrect password. Please try again.");
                return;
            }
            // Set the current customer to the one who logged in
            currentCustomer = foundCustomer;
            System.out.println("Login successful! Welcome, " + currentCustomer.getFirstName() + "!");
        } else {
            // User is already logged in, prompt for logout
            System.out.println("You are already logged in as " + currentCustomer.getUsername() + ".");
            System.out.println("Do you want to log out? (yes/no)");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("yes")) {
                // Log out the current user
                currentCustomer = null;
                System.out.println("Logout successful!");
            } else {
                System.out.println("Logout canceled.");
            }
        }
    }

    // Helper method to find a customer by username
    private Customer findCustomerByUsername(String username) {
        for (Customer customer : customers) {
            if(customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }

    /**
     * Refactored the bubbleSort method to use the mergeSort algorithm instead.
     * Sorts the products by the given variable in ascending or descending order.
     *
     * @param sortBy      the variable to sort by
     * @param isAscending true if the products should be sorted in ascending order, false if they should be sorted in descending order
     */
    public void mergeSort(String sortBy, boolean isAscending) {
        products = mergeSortHelper(products, sortBy, isAscending);
    }

    private ArrayList<Product> mergeSortHelper(ArrayList<Product> productList, String sortBy, boolean isAscending) {
        int size = productList.size();

        if(size <= 1) {
            return productList; // Already sorted
        }

        // Split the list into two halves
        int mid = size / 2;
        ArrayList<Product> left = new ArrayList<>(productList.subList(0, mid));
        ArrayList<Product> right = new ArrayList<>(productList.subList(mid, size));

        // Recursively sort the two halves
        left = mergeSortHelper(left, sortBy, isAscending);
        right = mergeSortHelper(right, sortBy, isAscending);

        // Merge the sorted halves
        return merge(left, right, sortBy, isAscending);
    }

    private ArrayList<Product> merge(ArrayList<Product> left, ArrayList<Product> right, String sortBy, boolean isAscending) {
        ArrayList<Product> mergedList = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if(compareProducts(left.get(leftIndex), right.get(rightIndex), sortBy, isAscending) < 0) {
                mergedList.add(left.get(leftIndex++));
            } else {
                mergedList.add(right.get(rightIndex++));
            }
        }

        // Add remaining elements from left and right
        mergedList.addAll(left.subList(leftIndex, left.size()));
        mergedList.addAll(right.subList(rightIndex, right.size()));

        return mergedList;
    }

    private int compareProducts(Product product1, Product product2, String sortBy, boolean isAscending) {
        if(sortBy.equals("name")) {
            return isAscending ? product1.getName().compareTo(product2.getName()) :
                    product2.getName().compareTo(product1.getName());
        } else if(sortBy.equals("price")) {
            return isAscending ? Double.compare(product1.getPrice(), product2.getPrice()) :
                    Double.compare(product2.getPrice(), product1.getPrice());
        }

        // Handle the case where sortBy is neither "name" nor "price"
        throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
    }

    /**
     * The navigateToWaresMenu method navigates to the wares menu.
     * It is refactored from the Run method.
     * It is encapsulated in the SeeWaresNavigationCommand class, using the Command pattern.
     */
    public void navigateToWaresMenu() {

        option1 = "See all wares";
        option2 = "Purchase a ware";
        option3 = "Sort wares";
        option4 = "Login";
        System.out.println("WARES MENU");
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        System.out.println("4: " + option4);
    }

    /**
     * The navigateToCustomerMenu method navigates to the customer menu.
     * It is refactored from the Run method.
     * It is encapsulated in the CustomerInfoNavigationCommand class, using the Command pattern.
     */
    public void navigateToCustomerMenu() {

        option1 = "See your orders";
        option2 = "See your info"; // OBS! Assignment typo: "Set your info", it is supposed to be "See your info" otherwise there is no corresponding code that sets your info!
        option3 = "Add funds";
        option4 = "Login";
        System.out.println("CUSTOMER MENU");
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        System.out.println("4: " + option4);
    }

    /**
     * The navigateToLoginMenu method navigates to the login menu.
     * It is refactored from the Run method.
     * It is encapsulated in the LoginMenuDisplayCommand class, using the Command pattern.
     */
    public void navigateToLoginMenu() {

        option1 = "Set Username";
        option2 = "Set Password";
        option3 = "Login";
        option4 = "Register";
        System.out.println("LOGIN MENU");
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        System.out.println("4: " + option4);
    }

    /**
     * The seeAllWares method shows all the wares.
     * It is a sub-menu of the wares menu.
     * It is refactored from the Run method.
     * It is encapsulated in the SeeAllWaresActionCommand class, using the Command pattern.
     */
    public void seeAllWares() {
        for (Product product : products) {
            product.PrintInfo();
        }
    }

    /**
     * The purchaseAWare method displays the purchase menu, which is a sub-menu of the wares menu.
     * It allows the user to purchase a ware if they are logged in and have enough funds.
     * It is refactored from the Run method.
     * It is encapsulated in the PurchaseAWareActionCommand class, using the Command pattern.
     */
    public void purchaseAWare() {
        if (currentCustomer != null) { // Check if user is logged in
            System.out.println("PURCHASE A WARE");
            System.out.println("Your funds: " + currentCustomer.getFunds());
            System.out.println("What would you like to purchase? Type in the number of the ware you want to purchase.");

            for (int i = 0; i < products.size(); i++) {
                System.out.println(i + 1 + ": " + products.get(i).getName() + ", " + products.get(i).getPrice() + "kr");
            }

            int currentChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            int index = currentChoice - 1;
            Product product = products.get(index);
            if (product.InStock()) {
                if (currentCustomer.CanAfford(product.getPrice())) {
                    currentCustomer.removeFunds(product.getPrice());
                    product.decreaseStock();
                    currentCustomer.getOrders().add(new Order(product.getName(), product.getPrice(), LocalDateTime.now()));
                    System.out.println();
                    System.out.println("Successfully bought " + product.getName());
                    System.out.println();
                } else {
                    System.out.println();
                    System.out.println("You cannot afford.");
                    System.out.println();
                }
            } else {
                System.out.println();
                System.out.println("Not in stock.");
                System.out.println();
            }
        } else {
            // If user is not logged in, prompt them to log in first
            System.out.println("Please log in to purchase a ware or register if you are a new user from Login Menu.");
        }
    }


    /**
     * The displaySortMenu method displays the sort menu, which is a sub-menu of the wares menu.
     * It is refactored from the Run method.
     * It is encapsulated in the SortMenuDisplayCommand class, using the Command pattern.
     */
    public void displaySortMenu() {
        option1 = "Sort by name, descending";
        option2 = "Sort by name, ascending";
        option3 = "Sort by price, descending";
        option4 = "Sort by price, ascending";
        System.out.println("SORT MENU");
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        System.out.println("4: " + option4);
    }

    /**
     * The seeYourOrders method displays the orders of the current customer, which is a sub-menu of the customer menu.
     * It is refactored from the Run method.
     * It is encapsulated in the SeeYourOrdersActionCommand class, using the Command pattern.
     */
    public void seeYourOrders() {
        if(currentCustomer != null){
            currentCustomer.PrintOrderHistory();
        }else {
            System.out.println("Please log in to see your orders.");
        }
    }

    /**
     * The seeYourInfo method displays the info of the current customer, which is a sub-menu of the customer menu.
     * It is refactored from the Run method, which was originally named PrintInfo.
     * It is encapsulated in the SeeYourInfoActionCommand class, using the Command pattern.
     */
    public void seeYourInfo() {
        if(currentCustomer != null){
            currentCustomer.PrintCustomerInfo();
        } else {
            System.out.println("Please log in or register to see your info.");
        }
    }

    /**
     * The addFundsToCustomerAccount method adds funds to the current customer's account.
     * It is refactored from the Run method.
     * It is encapsulated in the AddFundsActionCommand class, using the Command pattern.
     */
    public void addFundsToCustomerAccount() {
        int currentAmount = currentCustomer.getFunds();
        System.out.println("Your current funds: " + currentAmount);
        System.out.println("How much funds would you like to add? Please type in the amount");
        int amount = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        currentCustomer.addFunds(amount);
        System.out.println(amount + " is added to your profile." + " Your current funds: " + currentAmount+amount);
    }

    /**
     * The setUsername method sets the username of the current customer.
     * It is refactored from the Run method.
     * It is encapsulated in the SetUsernameActionCommand class, using the Command pattern.
     * It is refactored to use the Builder pattern.
     */
    public void setUsername() {
        if(currentCustomer != null) {
            System.out.println("A keyboard appears.");
            System.out.println("Please type in your username.");

            String newUsername = scanner.nextLine().trim();

            boolean isUsernameTaken = isUsernameTaken(newUsername);

            if(!isUsernameTaken) {
                currentCustomer = new Customer.Builder(newUsername)
                        .password(currentCustomer.getPassword())
                        .firstName(currentCustomer.getFirstName())
                        .lastName(currentCustomer.getLastName())
                        .email(currentCustomer.getEmail())
                        .age(currentCustomer.getAge())
                        .address(currentCustomer.getAddress())
                        .phoneNumber(currentCustomer.getPhoneNumber())
                        .funds(currentCustomer.getFunds())
                        .orders(currentCustomer.getOrders())
                        .build();
                System.out.println("Username changed to " + newUsername);
            } else {
                System.out.println("Username already taken. Please choose another one.");
            }
        } else {
            System.out.println("Please log in or register to set your username.");
        }
    }

    /**
     * The setPassword method sets the password of the current customer.
     * It is refactored from the Run method.
     * It is encapsulated in the SetPasswordActionCommand class, using the Command pattern.
     * It is refactored to use the Builder pattern.
     */
    public void setPassword() {
        if(currentCustomer != null) {
            System.out.println("A keyboard appears.");
            System.out.println("Please type in your password.");

            String newPassword = scanner.nextLine().trim();

            currentCustomer = new Customer.Builder(currentCustomer.getUsername())
                    .password(password)
                    .firstName(currentCustomer.getFirstName())
                    .lastName(currentCustomer.getLastName())
                    .email(currentCustomer.getEmail())
                    .age(currentCustomer.getAge())
                    .address(currentCustomer.getAddress())
                    .phoneNumber(currentCustomer.getPhoneNumber())
                    .funds(currentCustomer.getFunds())
                    .orders(currentCustomer.getOrders())
                    .build();

            System.out.println("A new password is created: " + newPassword);
        } else {
            System.out.println("Please log in or register to set your password.");
        }
    }
}
