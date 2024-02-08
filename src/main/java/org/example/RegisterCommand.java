package org.example;

public class RegisterCommand implements Command{
    private WebShop webShop;
    private String username;
    private String password;

    public RegisterCommand(WebShop webShop, String username, String password)
    {
        this.webShop = webShop;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        webShop.register(username, password);
    }
}
