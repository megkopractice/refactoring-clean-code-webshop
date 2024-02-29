package org.example;

public class LoginNavigationCommand implements Command{
    public WebShop webShop;

    public LoginNavigationCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.navigateToLoginMenu();
    }
}
