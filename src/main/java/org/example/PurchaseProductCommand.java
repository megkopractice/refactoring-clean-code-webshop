package org.example;

public class PurchaseProductCommand implements Command{
    private WebShop webShop;
    private Product product;

    public PurchaseProductCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.purchaseProduct();
    }
}
