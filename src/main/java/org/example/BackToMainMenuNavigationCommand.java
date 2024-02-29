package org.example;

public class BackToMainMenuNavigationCommand implements Command {

    private WebShop webShop;

    public BackToMainMenuNavigationCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displayMainMenu();
    }
}
