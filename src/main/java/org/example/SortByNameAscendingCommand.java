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

        // Then print the sorted list
        for(Product product: webShop.products){
            System.out.println(product.getName() + ": " + product.getPrice() + "kr, " + product.getNrInStock() + " in stock.");
        }
    }
}
