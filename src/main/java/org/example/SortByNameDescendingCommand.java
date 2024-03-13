package org.example;

public class SortByNameDescendingCommand implements Command {
    public WebShop webShop;

    public SortByNameDescendingCommand(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        // sort the list of products by name in descending order
        webShop.mergeSort("name", false);

        // Then print the sorted list
        for(Product product: webShop.products){
            System.out.println(product.getName() + ": " + product.getPrice() + "kr, " + product.getNrInStock() + " in stock.");
        }

    }
}
