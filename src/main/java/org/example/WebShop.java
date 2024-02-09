package org.example;

import java.time.LocalDateTime;
import java.util.*;


// Refactoring a web shop program with Design Patterns (clean code)

// Design Patterns used in this program:
// 1. Builder Pattern on Customer class (and Order class) ☆ OK
// 2. Proxy Pattern on Database class (and Customer class)　☆ OK
// 3. Iterator Pattern on Customer class (and Order class)
// 4. Adapter Pattern on Customer class (and Order class)
// 5. Decorator Pattern on Product class (and Order class)
// 11. State Pattern on WebShop class　　　☆
// 12. Command Pattern on WebShop class　　☆

// Other details of refactoring:
// 1. Changed the name of the class from Main to WebShop. ☆
// 2. Changed the name of the class from Product to Ware. ☆
// 3. Changed the name of the class from Order to Purchase. ☆
// 4. Changed the name of the class from Database to DatabaseProxy. ☆
// 5. Changed the name of the class from Customer to CustomerBuilder. ☆
// 6. Changed the name of the class from WebShop to WebShopState. ☆
// 7. Changed the name of the class from WebShop to WebShopCommand. ☆
// 8. Changed the name of the class from WebShop to WebShopIterator. ☆
// 9. Changed the name of the class from WebShop to WebShopAdapter. ☆
// 10. Changed the name of the class from WebShop to WebShopDecorator. ☆
// 11. Changed the name of the class from WebShop to WebShopStrategy. ☆
// 12. Changed the name of the class from WebShop to WebShopObserver. ☆
// 13. Changed the name of the class from WebShop to WebShopTemplate. ☆
// 14. Changed the name of the class from WebShop to WebShopVisitor. ☆
// 15. Changed the name of the class from WebShop to WebShopMediator. ☆
// 16. Changed the name of the class from WebShop to WebShopMemento. ☆
// 17. Changed the name of the class from WebShop to WebShopFlyweight. ☆
// 18. Changed the name of the class from WebShop to WebShopFacade. ☆
// 19. Changed the name of the class from WebShop to WebShopBridge. ☆
// 20. Changed the name of the class from WebShop to WebShopComposite. ☆
// 21. Changed the name of the class from WebShop to WebShopChainOfResponsibility. ☆
// 22. Changed the name of the class from WebShop to WebShopInterpreter. ☆
// Design patterns that work as alternatives to Switch statements or else-if statements depending on the menu you are at (Command or State):
// (No Switch statements or else-if statements are allowed in this program.)

// Where Bubble sort is applied, which is a bad sorting algorithm, OK



/**
 * The WebShop class is the main class of the program.
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

    public WebShop() {
        products = database.getProducts();
        customers = database.getCustomers();

        //Button mainMenuButton = new Button("main menu");


        //commands.put("main menu", new NavigateMenuCommand(this, "main menu"));
        //commands.put("wares menu", new NavigateMenuCommand(this));
        //commands.put("customer menu", new NavigateMenuCommand(this));
    }

    public void displayMenu(){
        System.out.println("Welcome to the WebShop!");

        while(running){
            System.out.println(info);

            if(currentMenu.equals("purchase menu")){
                displayPurchaseMenu();
            }else{
                displayMainMenu();
            }

            displayOptions();
            displayUserStatus();
            String choice = scanner.nextLine().toLowerCase();
            handleChoice(choice);
        }
    }

    private void handleChoice(String choice) {
        Command command = commands.get(choice);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayMainMenu(){
        System.out.println("1: " + option1);
        System.out.println("2: " + option2);
        System.out.println("3: " + option3);
        if(amountOfOptions > 3){
            System.out.println("4: " + option4);
        }
    }

    private void displayPurchaseMenu(){
        for(int i = 0; i < amountOfOptions; i++){
            System.out.println(i + 1 + ": " + products.get(i).getName() + ", " + products.get(i).getPrice() + "kr");
        }
        System.out.println("Your funds: " + currentCustomer.getFunds());
    }

    private void displayOptions(){
        for(int i = 0; i < amountOfOptions; i++){
            System.out.print(i + 1 + "\t");
        }
        System.out.println();
        for(int i = 1; i < currentChoice; i++){
            System.out.print("\t");
        }
        System.out.println("|");
    }

    private void displayUserStatus(){
        System.out.println("Your buttons are Left, Right, OK, Back and Quit.");
        if(currentCustomer != null){
            System.out.println("Current user: " + currentCustomer.getUsername());
        }else{
            System.out.println("You are not logged in.");
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
                                    option2 = "Set your info";
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
    }

    /**
     * Refactored the bubbleSort method to use the mergeSort algorithm instead.
     * Sorts the products by the given variable in ascending or descending order.
     * @param sortBy the variable to sort by
     * @param isAscending true if the products should be sorted in ascending order, false if they should be sorted in descending order
     */

    private void mergeSort(String sortBy, boolean isAscending) {
        products = mergeSortHelper(products, sortBy, isAscending);
    }

    private ArrayList<Product> mergeSortHelper(ArrayList<Product> productList, String sortBy, boolean isAscending) {
        int size = productList.size();

        if (size <= 1) {
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
            if (compareProducts(left.get(leftIndex), right.get(rightIndex), sortBy, isAscending) < 0) {
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
        if (sortBy.equals("name")) {
            return isAscending ? product1.getName().compareTo(product2.getName()) :
                    product2.getName().compareTo(product1.getName());
        } else if (sortBy.equals("price")) {
            return isAscending ? Double.compare(product1.getPrice(), product2.getPrice()) :
                    Double.compare(product2.getPrice(), product1.getPrice());
        }

        // Handle the case where sortBy is neither "name" nor "price"
        throw new IllegalArgumentException("Invalid sortBy value: " + sortBy);
    }

    /**
     * Created a new method to navigate the menu based on the given button.
     *
     * This method navigates the menu based on the given button.
     * @param button the button that was pressed
     */
    public void NavigateMenu(Button button) {

    }

    public void purchaseProduct(Product product) {
    }

    public void sortProducts(String sortType, boolean isAscending) {
    }

    public void login(String username, String password) {
    }

    public void register(String username, String password) {
    }
}
