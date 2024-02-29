package org.example;

public class QuitButtonCommand implements Command {
    private WebShop webShop;

    public QuitButtonCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        System.out.println("Exiting the WebShop application. Goodbye!");
        webShop.running = false; // Set the running flag in the WebShop class to false
    }
}
