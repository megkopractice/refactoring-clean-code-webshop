package org.example;

public class SortByPriceDescendingCommand implements Command{
    public WebShop webShop;

    public SortByPriceDescendingCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.mergeSort("price", false);
    }
}
