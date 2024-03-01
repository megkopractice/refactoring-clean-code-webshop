package org.example;

public class RegisterActionCommand implements Command{
    public WebShop webShop;

    public RegisterActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.register();
    }
}
