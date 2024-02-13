package org.example;

public class Main {
    public static void main(String[] args) {
        WebShop webShop = new WebShop();

        CommandHandler navigateMenuHandler = new CommandHandler();
        CommandHandler purchaseProductHandler = new CommandHandler();
        CommandHandler sortProductHandler = new CommandHandler();
        CommandHandler loginHandler = new CommandHandler();
        CommandHandler registerHandler = new CommandHandler();

        navigateMenuHandler.invoke(new NavigateMenuCommand(webShop));

    }
}