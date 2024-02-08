package org.example;

public class NavigateMenuCommand implements Command{
    private WebShop webShop;
    private Button button;

    public NavigateMenuCommand(WebShop webShop, Button button)
    {
        this.webShop = webShop;
        this.button = button;
    }

    @Override
    public void execute() {
        webShop.NavigateMenu(button);
    }
}
