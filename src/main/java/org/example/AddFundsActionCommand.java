package org.example;

public class AddFundsActionCommand implements Command{
    public WebShop webShop;

    public AddFundsActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.addFundsToCustomerAccount();
    }
}
