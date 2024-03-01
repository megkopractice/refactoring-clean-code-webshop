package org.example;

public class LoginActionCommand implements Command{

    private WebShop webShop;

    public LoginActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.login();
    }
}
