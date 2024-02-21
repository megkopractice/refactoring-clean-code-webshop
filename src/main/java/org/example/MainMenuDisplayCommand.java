package org.example;

/**
 * Command to display the main menu
 */
public class MainMenuDisplayCommand implements Command{
    private WebShop webShop;


    public MainMenuDisplayCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displayMenu();
    }
}
