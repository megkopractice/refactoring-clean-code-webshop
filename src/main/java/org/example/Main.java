package org.example;

public class Main {
    public static void main(String[] args) {
        WebShop webShop = new WebShop();
        //Command currentCommand = webShop.commands.get(new Button("default menu"));

        Command defaultUIDisplay = new DefaultUIDisplayCommand(webShop);
        Command purchaseAware = new PurchaseAWareActionCommand(webShop);
        Command sortByNameAscending = new SortByNameAscendingCommand(webShop);
        Command sortByNameDescending = new SortByNameDescendingCommand(webShop);
        Command sortByPriceAscending = new SortByPriceAscendingCommand(webShop);
        Command sortByPriceDescending = new SortByPriceDescendingCommand(webShop);
        Command loginMenuDisplay = new LoginMenuDisplayCommand(webShop);
        Command register = new RegisterActionCommand(webShop);

        CommandHandler commandHandler = new CommandHandler();
        // Display the main menu (1. See Wares, 2. Customer Info 3. Login)
        /*
        if(currentCommand.equals(webShop.commands.get(new Button("default menu")))){
            defaultUIHandler.invoke(new DefaultUIDisplayCommand(webShop));
        }*/

        commandHandler.invoke(defaultUIDisplay);
        commandHandler.invoke(purchaseAware);
        commandHandler.invoke(sortByNameAscending);
        commandHandler.invoke(sortByNameDescending);
        commandHandler.invoke(sortByPriceAscending);
        commandHandler.invoke(sortByPriceDescending);
        commandHandler.invoke(loginMenuDisplay);
        commandHandler.invoke(register);

    }
}