package org.example;

public class PurchaseProductCommand implements Command{
    private WebShop webShop;
    private Product product;
    private Button button;

    public PurchaseProductCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {

    }

    @Override
    public void execute(String choice) {
        if(button.getName().equals("purchase a ware")){
            webShop.displayPurchaseMenu();
        }
        if(button.getName().equals("purchase menu")){
            webShop.purchaseProduct();
        }
    }
}
