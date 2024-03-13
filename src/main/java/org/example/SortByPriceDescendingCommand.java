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

        // Then print the sorted list
        for(Product product: webShop.products){
            System.out.println(product.getName() + ": " + product.getPrice() + "kr, " + product.getNrInStock() + " in stock.");
        }
    }
}
