package org.example;

public class PurchaseProductCommand implements Command{
    private WebShop webShop;
    private Product product;

    public PurchaseProductCommand(WebShop webShop, Product product) {
        this.webShop = webShop;
        this.product = product;
    }

    @Override
    public void execute() {
        webShop.purchaseProduct(product);
    }
}
