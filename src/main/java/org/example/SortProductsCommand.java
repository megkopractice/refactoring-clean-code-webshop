package org.example;

public class SortProductsCommand  implements Command{
    private WebShop webShop;
    private String sortBy;
    private boolean isAscending;

    public SortProductsCommand(WebShop webShop, String sortBy, boolean isAscending) {
        this.webShop = webShop;
        this.sortBy = sortBy;
        this.isAscending = isAscending;
    }

    @Override
    public void execute() {
        webShop.mergeSort(sortBy, isAscending);
    }

    @Override
    public void execute(String choice) {

    }
}
