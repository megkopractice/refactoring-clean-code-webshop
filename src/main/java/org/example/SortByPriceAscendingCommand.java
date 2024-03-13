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

        // Then print the sorted list
        for(Product product: webShop.products){
            System.out.println(product.getName() + ": " + product.getPrice() + "kr, " + product.getNrInStock() + " in stock.");
        }
    }
}
