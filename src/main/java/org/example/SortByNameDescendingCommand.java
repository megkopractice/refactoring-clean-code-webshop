package org.example;

public class SortByNameDescendingCommand implements Command {
    public WebShop webShop;

    public SortByNameDescendingCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.mergeSort("name", false);
    }
}
