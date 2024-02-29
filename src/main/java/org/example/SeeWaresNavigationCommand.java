package org.example;

public class SeeWaresNavigationCommand implements Command{

    private WebShop webShop;

    public SeeWaresNavigationCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.navigateToWaresMenu(); // Call the method in the WebShop class to navigate to the wares menu
    }
}
