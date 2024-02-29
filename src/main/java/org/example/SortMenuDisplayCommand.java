package org.example;

public class SortMenuDisplayCommand implements Command{
    public WebShop webShop;

    public SortMenuDisplayCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.displaySortMenu();
    }
}
