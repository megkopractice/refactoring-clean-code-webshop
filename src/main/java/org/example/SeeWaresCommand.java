package org.example;

public class SeeWaresCommand implements Command{
    private WebShop webShop;

    public SeeWaresCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.showWares();
    }

}
