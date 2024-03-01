package org.example;

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
 *    - Command classes: NavigateMenuCommand, PurchaseProductCommand, RegisterCommand, LoginCommand, LogoutCommand, SortWaresCommand　
 * 4. Merge Sort algorithm on mergeSort method in WebShop class ☆ OK
 * @author Megumi Kogo
 */

public class WebShop {
    boolean running = true;
    Database database = new Database();
    ArrayList<Product> products = new ArrayList<Product>();
    ArrayList<Customer> customers = new ArrayList<Customer>();

    String currentMenu = "main menu";
    int currentChoice = 1;
    int amountOfOptions = 3;
    String option1 = "See Wares";
    String option2 = "Customer Info";
    String option3 = "Login";
    String option4 = "";
    String info = "What would you like to do?";

    String username = null;
    String password = null;
    Customer currentCustomer;

    Scanner scanner = new Scanner(System.in);

    private Map<Button, Command> commands = new HashMap<>();
    private List<Runnable> purchaseActions;

    public WebShop() {
        products = database.getProducts();
        customers = database.getCustomers();

        // Back / Quit Button

        Button backToMainButton = new Button("back");
        commands.put(backToMainButton, new BackToMainMenuNavigationCommand(this));
        Button quitButton = new Button("quit"); // ok
        commands.put(quitButton, new QuitButtonCommand(this)); // ok

        // Buttons for the main menu
        Button defaultMenuButton = new Button("default menu"); // ok
        commands.put(defaultMenuButton, new DefaultUIDisplayCommand(this)); // ok
        Button seeWearsButton = new Button("see wares");
        commands.put(seeWearsButton, new SeeWaresNavigationCommand(this));// ok
        Button customerInfoButton = new Button("customer info");
        commands.put(customerInfoButton, new CustomerInfoNavigationCommand(this));// ok
        Button loginMenuButton = new Button("login");
        commands.put(loginMenuButton, new LoginMenuDisplayCommand(this));// ok

        // Buttons for the wares menu
        Button seeAllWaresButton = new Button("see all wares");
        commands.put(seeAllWaresButton, new SeeAllWaresActionCommand(this));// ok
        Button purchaseWareButton = new Button("purchase a ware");
        commands.put(purchaseWareButton, new PurchaseAWareActionCommand(this));// ok
        Button sortWaresButton = new Button("sort wares");
        commands.put(sortWaresButton, new SortMenuDisplayCommand(this)); // ok. This should display sort wares

        // Buttons for the sort wares menu (wares menu -> sort wares
        Button sortByNameDescendingButton = new Button("sort by name, descending");
        commands.put(sortByNameDescendingButton, new SortByNameDescendingCommand(this)); // ok
        Button sortByNameAscendingButton = new Button("sort by name, ascending");
        commands.put(sortByNameAscendingButton, new SortByNameAscendingCommand(this)); // ok
        Button sortByPriceDescendingButton = new Button("sort by price, descending");
        commands.put(sortByPriceDescendingButton, new SortByPriceDescendingCommand(this)); // ok
        Button sortByPriceAscendingButton = new Button("sort by price, ascending");
        commands.put(sortByPriceAscendingButton, new SortByPriceAscendingCommand(this)); // ok

        // Buttons for the customer menu
        Button seeOrdersButton = new Button("see your orders");
        commands.put(seeOrdersButton, new SeeYourOrdersActionCommand(this)); // ok
        Button seeInfoButton = new Button("set your info");
        commands.put(seeInfoButton, new SeeYourInfoActionCommand(this)); // ok
        Button addFundsButton = new Button("add funds");
        commands.put(addFundsButton, new AddFundsActionCommand(this)); // ok

        // Buttons for the login menu
        Button setUsernameButton = new Button("set username");
        commands.put(setUsernameButton, new SetUsernameActionCommand(this));// ok
        Button setPasswordButton = new Button("set password");
        commands.put(setPasswordButton, new SetPasswordActionCommand(this));// ok
        Button loginButton = new Button("login");
        commands.put(loginButton, new LoginActionCommand(this)); // ok
        Button registerButton = new Button("register");
        commands.put(registerButton, new RegisterActionCommand(this)); // ok

    }

    /**
     * The displayMenu method displays the menu and handles the user's input.
     * It is the main method of the program.
     * It is refactored from the Run method.
     * It is refactored to use the Command pattern.
     */
    public void displayDefaultMenu() {
        System.out.println("Welcome to the WebShop!");
        while (running) {

            if(currentMenu.equals("purchase menu")) {
                displayPurchaseMenu();
            } else {
                displayMainMenu();
            }

            System.out.println(info);
            displayButtonInstructions();
            displayUserStatus();
            String choice = scanner.nextLine().trim().toLowerCase();
            handleOption(choice);
        }
    }

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


    /*
    // Refactored the Run method to displayMenu method
    public void displayMenu() {
        System.out.println("Welcome to the WebShop!");

        while (running) {
            System.out.println(info);

            if(currentMenu.equals("purchase menu")) {
                displayPurchaseMenu();
            } else {
                displayMainMenu();
            }

            displayOptions();
            displayButtonInstructions();
            displayUserStatus();
            String choice = scanner.nextLine().toLowerCase();
            handleChoice(choice);
        }
    }*/


    // Refactored the Run method to displayMainMenu method
    public void displayMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        if(amountOfOptions > 3) {
            System.out.println("4: " + option4);
        }
    }

    /**
     * The displayPurchaseMenu method displays the purchase menu, which is a sub-menu of the wares menu.
     * It is refactored from the Run method.
     * It is encapsulated in the PurchaseAWareActionCommand class, using the Command pattern.
     */
    public void displayPurchaseMenu() {
        for (int i = 0; i < amountOfOptions; i++) {
            System.out.println(i + 1 + ": " + products.get(i).getName() + ", " + products.get(i).getPrice() + "kr");
        }
        System.out.println("Your funds: " + currentCustomer.getFunds());

        // Display options
        //displayOptions();

        // Process user input
        String choice = scanner.nextLine().toLowerCase();
        try {
            int option = Integer.parseInt(choice);
            if (option >= 1 && option <= purchaseActions.size()) {
                purchaseActions.get(option - 1).run(); // Execute the corresponding action
            } else {
                System.out.println("Invalid option. Please try again.");
                displayPurchaseMenu(); // Redisplay the menu
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            displayPurchaseMenu(); // Redisplay the menu
        }
    }

    /*
    // Refactored the Run method to displayOptions method
    private void displayOptions() {
        for (int i = 0; i < amountOfOptions; i++) {
            System.out.print(i + 1 + "\t");
        }
        System.out.println();
        for (int i = 1; i < currentChoice; i++) {
            System.out.print("\t");
        }
        System.out.println("|");
    }*/

    // Refactored the Run method to displayButtonInstructions method
    private void displayButtonInstructions() {
        System.out.println("Your buttons are : [Option Name] | Back | Quit");
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
     * The purchaseProduct method purchases a product.
     * It is refactored from the Run method.
     */
    public void purchaseProduct() {
        if(currentMenu.equals("purchase menu")) {
            int index = currentChoice - 1;
            Product product = products.get(index);
            if(product.InStock()) {
                if(currentCustomer.CanAfford(product.getPrice())) {
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
        }
    }

    /**
     * The register method registers a new customer.
     * Newly added method, using the Builder pattern.
     */
    public void register() {
        // Check if the username is already taken
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
        int ageReg = scanner.nextInt();
        System.out.println("Please write your address.");
        String addressReg = scanner.nextLine();
        System.out.println("Please write your phone number.");
        String phoneNumberReg = scanner.nextLine();

        // Create a new customer using the Builder pattern
        Customer newCustomer = new Customer.Builder(username)
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
     * The login method logs in a customer.
     * Newly added method, using the Builder pattern.
     */
    public void login() {
        System.out.println("Please type in your username.");
        String usernameInput = scanner.nextLine();
        System.out.println("Please type in your password.");
        String passwordInput = scanner.nextLine();
        Customer foundCustomer = findCustomerByUsername(usernameInput);

        // Check if the username exists
        if(foundCustomer == null) {
            System.out.println("Username not found. Please register if you are a new user.");
            return;
        }
        // Check if the password matches
        if(!foundCustomer.CheckPassword(passwordInput)) {
            System.out.println("Incorrect password. Please try again.");
            return;
        }

        // Set the current customer to the one who logged in
        currentCustomer = foundCustomer;
        System.out.println("Login successful! Welcome, " + currentCustomer.getFirstName() + "!");
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

    // Method to log out the current customer
    public void logout() {
        currentCustomer = null;
        System.out.println("Logout successful!");
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
        option4 = "Login/logout";
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
        option4 = "Login/logout";
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
     * The seeAllWares method navigates to the main menu.
     * It is refactored from the Run method.
     * It is encapsulated in the SeeAllWaresActionCommand class, using the Command pattern.
     */
    public void seeAllWares() {
        for (Product product : products) {
            product.PrintInfo();
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
        currentCustomer.PrintOrderHistory();
    }

    /**
     * The seeYourInfo method displays the info of the current customer, which is a sub-menu of the customer menu.
     * It is refactored from the Run method, which was originally named PrintInfo.
     * It is encapsulated in the SeeYourInfoActionCommand class, using the Command pattern.
     */
    public void seeYourInfo() {
        currentCustomer.PrintCustomerInfo();
    }

    /**
     * The addFundsToCustomerAccount method adds funds to the current customer's account.
     * It is refactored from the Run method.
     * It is encapsulated in the AddFundsActionCommand class, using the Command pattern.
     */
    public void addFundsToCustomerAccount() {
        int currentAmount = currentCustomer.getFunds();
        System.out.println("Your current funds: " + currentAmount);
        System.out.println("How much funds would you like to add?");
        int amount = scanner.nextInt();
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
        System.out.println("A keyboard appears.");
        System.out.println("Please type in your username.");
        String newUsername = scanner.nextLine();
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
    }

    /**
     * The setPassword method sets the password of the current customer.
     * It is refactored from the Run method.
     * It is encapsulated in the SetPasswordActionCommand class, using the Command pattern.
     * It is refactored to use the Builder pattern.
     */
    public void setPassword() {
        System.out.println("A keyboard appears.");
        System.out.println("Please type in your password.");
        password = scanner.nextLine();
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
    }




}

    /**
     * The Run method is the main method of the program.
     */
    /*public void Run() {
        System.out.println("Welcome to the WebShop!");
        while (running) {
            System.out.println(info);

            if (currentMenu.equals("purchase menu")) {
                for (int i = 0; i < amountOfOptions; i++) {
                    System.out.println(i + 1 + ": " + products.get(i).getName() + ", " + products.get(i).getPrice() + "kr");
                }
                System.out.println("Your funds: " + currentCustomer.getFunds());
            } else {
                System.out.println("1: " + option1);
                System.out.println("2: " + option2);
                if (amountOfOptions > 2) {
                    System.out.println("3: " + option3);
                }
                if (amountOfOptions > 3) {
                    System.out.println("4: " + option4);
                }
            }

            for (int i = 0; i < amountOfOptions; i++) {
                System.out.print(i + 1 + "\t");
            }
            System.out.println();
            for (int i = 1; i < currentChoice; i++) {
                System.out.print("\t");
            }
            System.out.println("|");

            System.out.println("Your buttons are Left, Right, OK, Back and Quit.");
            if (currentCustomer != null) {
                System.out.println("Current user: " + currentCustomer.getUsername());
            } else {
                System.out.println("You are not logged in.");
            }

            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "left":
                case "l":
                    if (currentChoice > 1) {
                        currentChoice--;
                    }
                    break;
                case "right":
                case "r":
                    if (currentChoice < amountOfOptions) {
                        currentChoice++;
                    }
                    break;
                case "ok":
                case "k":
                case "o":
                    if (currentMenu.equals("main menu")) {
                        switch (currentChoice) {
                            case 1:
                                option1 = "See all wares";
                                option2 = "Purchase a ware";
                                option3 = "Sort wares";
                                if (currentCustomer == null) {
                                    option4 = "Login";
                                } else {
                                    option4 = "Logout";
                                }
                                amountOfOptions = 4;
                                currentChoice = 1;
                                currentMenu = "wares menu";
                                info = "What would you like to do?";
                                break;
                            case 2:
                                if (currentCustomer != null) {
                                    option1 = "See your orders";
                                    option2 = "Set your info"; // maybe this was supposed to be See your info
                                    option3 = "Add funds";
                                    option4 = "";
                                    amountOfOptions = 3;
                                    currentChoice = 1;
                                    info = "What would you like to do?";
                                    currentMenu = "customer menu";
                                } else {
                                    System.out.println();
                                    System.out.println("You are not logged in.");
                                    System.out.println();
                                }
                                break;
                            case 3:
                                if (currentCustomer == null) {
                                    option1 = "Set Username";
                                    option2 = "Set Password";
                                    option3 = "Login";
                                    option4 = "Register";
                                    amountOfOptions = 4;
                                    currentChoice = 1;
                                    info = "Please submit username and password.";
                                    username = null;
                                    password = null;
                                    currentMenu = "login menu";
                                } else {
                                    option3 = "Logout"; // Changed from "Login". Detected a logical error here.
                                    System.out.println();
                                    System.out.println(currentCustomer.getUsername() + " logged out.");
                                    System.out.println();
                                    currentChoice = 1;
                                    currentCustomer = null;
                                }
                                break;
                            default:
                                System.out.println();
                                System.out.println("There is no such option. Choose from the available options.");
                                System.out.println();
                                break;
                        }
                    } else if (currentMenu.equals("customer menu")) {
                        switch (currentChoice) {
                            case 1:
                                currentCustomer.PrintOrders();
                                break;
                            case 2:
                                currentCustomer.PrintInfo();
                                break;
                            case 3:
                                System.out.println("How much funds would you like to add?");
                                String amountString = scanner.nextLine();
                                try {
                                    int amount = Integer.parseInt(amountString);
                                    if (amount < 0) {
                                        System.out.println();
                                        System.out.println("Impossible to add negative amounts.");
                                        System.out.println();
                                    } else {
                                        currentCustomer.addFunds(amount);
                                        System.out.println();
                                        System.out.println(amount + " added to your profile.");
                                        System.out.println();
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println();
                                    System.out.println("Please type in amounts.");
                                    System.out.println();
                                }
                                break;
                            default:
                                System.out.println();
                                System.out.println("There is no such option. Choose from the available options.");
                                System.out.println();
                                break;
                        }
                    } else if (currentMenu.equals("sort menu")) {
                        boolean back = true;
                        switch (currentChoice) {
                            case 1:
                                mergeSort("name", false);
                                System.out.println();
                                System.out.println("Wares sorted.");
                                System.out.println();
                                break;
                            case 2:
                                mergeSort("name", true);
                                System.out.println();
                                System.out.println("Wares sorted.");
                                System.out.println();
                                break;
                            case 3:
                                mergeSort("price", false);
                                System.out.println();
                                System.out.println("Wares sorted.");
                                System.out.println();
                                break;
                            case 4:
                                mergeSort("price", true);
                                System.out.println();
                                System.out.println("Wares sorted.");
                                System.out.println();
                                break;
                            default:
                                back = false;
                                System.out.println();
                                System.out.println("There is no such option. Choose from the available options.");
                                System.out.println();
                                break;
                        }
                        if (back) {
                            option1 = "See all wares";
                            option2 = "Purchase a ware";
                            option3 = "Sort wares";
                            if (currentCustomer == null) {
                                option4 = "Login";
                            } else {
                                option4 = "Logout";
                            }
                            amountOfOptions = 4;
                            currentChoice = 1;
                            currentMenu = "wares menu";
                            info = "What would you like to do?";
                        }
                    } else if (currentMenu.equals("wares menu")) {
                        switch (currentChoice) {
                            case 1:
                                System.out.println();
                                for (Product product : products) {
                                    product.PrintInfo();
                                }
                                System.out.println();
                                break;
                            case 2:
                                if (currentCustomer != null) {
                                    currentMenu = "purchase menu";
                                    info = "What would you like to purchase?";
                                    currentChoice = 1;
                                    amountOfOptions = products.size();
                                } else {
                                    System.out.println();
                                    System.out.println("You must be logged in to purchase wares.");
                                    System.out.println();
                                    currentChoice = 1;
                                }
                                break;
                            case 3:
                                option1 = "Sort by name, descending";
                                option2 = "Sort by name, ascending";
                                option3 = "Sort by price, descending";
                                option4 = "Sort by price, ascending";
                                info = "How would you like to sort them?";
                                currentMenu = "sort menu";
                                currentChoice = 1;
                                amountOfOptions = 4;
                                break;
                            case 4:
                                if (currentCustomer == null) {
                                    option1 = "Set Username";
                                    option2 = "Set Password";
                                    option3 = "Login";
                                    option4 = "Register";
                                    amountOfOptions = 4;
                                    info = "Please submit username and password.";
                                    currentMenu = "login menu";
                                    currentChoice = 1;
                                } else {
                                    option4 = "Logout";  // Changed from "Login". Detected a logical error here.
                                    System.out.println();
                                    System.out.println(currentCustomer.getUsername() + " logged out.");
                                    System.out.println();
                                    currentCustomer = null;
                                    currentChoice = 1;
                                }
                                break;
                            case 5:
                                break;
                            default:
                                System.out.println();
                                System.out.println("There is no such option. Choose from the available options.");
                                System.out.println();
                                break;
                        }
                    } else if (currentMenu.equals("login menu")) {
                        switch (currentChoice) {
                            case 1:
                                System.out.println("A keyboard appears.");
                                System.out.println("Please type in your username.");
                                username = scanner.nextLine();
                                System.out.println();
                                break;
                            case 2:
                                System.out.println("A keyboard appears.");
                                System.out.println("Please type in your password.");
                                password = scanner.nextLine();
                                System.out.println();
                                break;
                            case 3:
                                if (username == null || password == null) {
                                    System.out.println();
                                    System.out.println("Incomplete data.");
                                    System.out.println();
                                } else {
                                    boolean found = false;
                                    for (Customer customer : customers) {
                                        if (username.equals(customer.getUsername()) && customer.CheckPassword(password)) {
                                            System.out.println();
                                            System.out.println(customer.getUsername() + " logged in.");
                                            System.out.println();
                                            currentCustomer = customer;
                                            found = true;
                                            option1 = "See Wares";
                                            option2 = "Customer Info";
                                            if (currentCustomer == null) {
                                                option3 = "Login";
                                            } else {
                                                option3 = "Logout";
                                            }
                                            info = "What would you like to do?";
                                            currentMenu = "main menu";
                                            currentChoice = 1;
                                            amountOfOptions = 3;
                                            break;
                                        }
                                    }
                                    if (found == false) {
                                        System.out.println();
                                        System.out.println("Invalid credentials.");
                                        System.out.println();
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Please write your username.");
                                String newUsername = scanner.nextLine();
                                for (Customer customer : customers) {
                                    if (customer.getUsername().equals(username)) {
                                        System.out.println();
                                        System.out.println("Username already exists.");
                                        System.out.println();
                                        break;
                                    }
                                }
                                // Would have liked to be able to quit at any time in here.
                                choice = "";
                                boolean next = false;
                                String newPassword = null;
                                String firstName = null;
                                String lastName = null;
                                String email = null;
                                int age = -1;
                                String address = null;
                                String phoneNumber = null;
                                while (true) {
                                    System.out.println("Do you want a password? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your password.");
                                            newPassword = scanner.nextLine();
                                            if (newPassword.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want a first name? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your first name.");
                                            firstName = scanner.nextLine();
                                            if (firstName.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want a last name? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your last name.");
                                            lastName = scanner.nextLine();
                                            if (lastName.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want an email? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your email.");
                                            email = scanner.nextLine();
                                            if (email.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want an age? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your age.");
                                            String ageString = scanner.nextLine();
                                            try {
                                                age = Integer.parseInt(ageString);
                                            } catch (NumberFormatException e) {
                                                System.out.println();
                                                System.out.println("Please write a number.");
                                                System.out.println();
                                                continue;
                                            }
                                            next = true;
                                            break;
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want an address? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your address.");
                                            address = scanner.nextLine();
                                            if (address.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        next = false;
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }
                                while (true) {
                                    System.out.println("Do you want a phone number? y/n");
                                    choice = scanner.nextLine();
                                    if (choice.equals("y")) {
                                        while (true) {
                                            System.out.println("Please write your phone number.");
                                            phoneNumber = scanner.nextLine();
                                            if (phoneNumber.equals("")) {
                                                System.out.println();
                                                System.out.println("Please actually write something.");
                                                System.out.println();
                                                continue;
                                            } else {
                                                next = true;
                                                break;
                                            }
                                        }
                                    }
                                    if (choice.equals("n") || next) {
                                        break;
                                    }
                                    System.out.println();
                                    System.out.println("y or n, please.");
                                    System.out.println();
                                }

                                // Create a new customer using the Builder pattern
                                Customer newCustomer = new Customer.Builder(newUsername)
                                        .password(newPassword)
                                        .firstName(firstName)
                                        .lastName(lastName)
                                        .email(email)
                                        .age(age)
                                        .address(address)
                                        .phoneNumber(phoneNumber)
                                        .build();
                                customers.add(newCustomer);
                                currentCustomer = newCustomer;
                                System.out.println();
                                System.out.println(newCustomer.getUsername() + " successfully added and is now logged in.");
                                System.out.println();
                                option1 = "See Wares";
                                option2 = "Customer Info";
                                if (currentCustomer == null) {
                                    option3 = "Login";
                                } else {
                                    option3 = "Logout";
                                }
                                info = "What would you like to do?";
                                currentMenu = "main menu";
                                currentChoice = 1;
                                amountOfOptions = 3;
                                break;
                            default:
                                System.out.println();
                                System.out.println("Not an option.");
                                System.out.println();
                                break;
                        }
                    } else if (currentMenu.equals("purchase menu")) {
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
                    }
                    break;
                case "back":
                case "b":
                    if (currentMenu.equals("main menu")) {
                        System.out.println();
                        System.out.println("You're already on the main menu.");
                        System.out.println();
                    } else if (currentMenu.equals("wares menu")) { // Detected a logical error here. Changed from "purchase menu".
                        option1 = "See all wares";
                        option2 = "Purchase a ware";
                        option3 = "Sort wares";
                        if (currentCustomer == null) {
                            option4 = "Login";
                        } else {
                            option4 = "Logout";
                        }
                        amountOfOptions = 4;
                        currentChoice = 1;
                        currentMenu = "wares menu";
                        info = "What would you like to do?";
                    } else {
                        option1 = "See Wares";
                        option2 = "Customer Info";
                        if (currentCustomer == null) {
                            option3 = "Login";
                        } else {
                            option3 = "Logout";
                        }
                        info = "What would you like to do?";
                        currentMenu = "main menu";
                        currentChoice = 1;
                        amountOfOptions = 3;
                    }
                    break;
                case "quit":
                case "q":
                    System.out.println("The console powers down. You are free to leave.");
                    return;
                default:
                    System.out.println("That is not an applicable option.");
                    break;
            }
        }
    } // End of Run method

}*/
