package org.example;

public class DefaultUIDisplayCommand implements Command{
    private WebShop webShop;

    public DefaultUIDisplayCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displayDefaultMenu();
    }
}
