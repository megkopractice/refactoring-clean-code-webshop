package org.example;

public class SetUsernameActionCommand implements Command{

    public WebShop webShop;

    public SetUsernameActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.setUsername();
    }
}
