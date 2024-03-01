package org.example;

public class LoginMenuDisplayCommand implements Command {

    public WebShop webShop;

    public LoginMenuDisplayCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.navigateToLoginMenu();
    }

}
