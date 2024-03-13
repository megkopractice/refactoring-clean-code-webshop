package org.example;

public class PurchaseAWareActionCommand implements Command{
    public WebShop webShop;

    public PurchaseAWareActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.purchaseAWare();
    }
}
