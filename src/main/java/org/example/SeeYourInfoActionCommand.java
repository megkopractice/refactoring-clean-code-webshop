package org.example;

public class SeeYourInfoActionCommand implements Command{
    public WebShop webShop;

    public SeeYourInfoActionCommand(WebShop webShop)
    {
        this.webShop = webShop;
    }

    @Override
    public void execute() {
        webShop.seeYourInfo();
    }
}
