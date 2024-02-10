package org.example;

public class NavigateMenuCommand implements Command{
    private WebShop webShop;
    private Button button;

    public NavigateMenuCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displayMenu();
    }
}
