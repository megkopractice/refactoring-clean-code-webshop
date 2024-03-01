package org.example;

public class Main {
    public static void main(String[] args) {
        WebShop webShop = new WebShop();

        CommandHandler defaultUIHandler = new CommandHandler();
        CommandHandler purchaseProductHandler = new CommandHandler();
        CommandHandler sortProductsHandler = new CommandHandler();
        CommandHandler loginHandler = new CommandHandler();
        CommandHandler registerHandler = new CommandHandler();

        // Display the main menu (1. See Wares, 2. Customer Info 3. Login)
        defaultUIHandler.invoke(new DefaultUIDisplayCommand(webShop));

        //purchaseProductHandler.invoke(new PurchaseAWareActionCommand(webShop));



    }
}