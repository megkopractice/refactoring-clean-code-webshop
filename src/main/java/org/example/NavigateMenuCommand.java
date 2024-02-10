package org.example;

/**
 * Command to navigate the menu
 */
public class NavigateMenuCommand implements Command{
    private WebShop webShop;

    public NavigateMenuCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displayMenu();
    }
}
