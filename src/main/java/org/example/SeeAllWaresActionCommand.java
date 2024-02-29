package org.example;

public class SeeAllWaresActionCommand implements Command{
    public WebShop webShop;

    public SeeAllWaresActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.seeAllWares();
    }
}
