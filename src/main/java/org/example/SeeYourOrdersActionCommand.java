package org.example;

public class SeeYourOrdersActionCommand implements Command{
    public WebShop webShop;

    public SeeYourOrdersActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {

        webShop.seeYourOrders();
    }
}
