package org.example;

public class SortByPriceAscendingCommand implements Command{
    public WebShop webShop;

    public SortByPriceAscendingCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.mergeSort("price", true);
    }
}
