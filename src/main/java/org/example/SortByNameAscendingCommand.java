package org.example;

public class SortByNameAscendingCommand implements Command{
    public WebShop webShop;

    public SortByNameAscendingCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.mergeSort("name", true);
    }
}
