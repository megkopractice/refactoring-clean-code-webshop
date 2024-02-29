package org.example;

public class CustomerInfoNavigationCommand implements Command{

    public WebShop webShop;

    public CustomerInfoNavigationCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.navigateToCustomerMenu();
    }
}
