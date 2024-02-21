package org.example;

public class LoginCommand implements Command{
    private WebShop webShop;
    private String username;
    private String password;

    public LoginCommand(WebShop webShop, String username, String password)
    {
        this.webShop = webShop;
        this.username = username;
        this.password = password;
    }

    @Override
    public void execute() {
        webShop.login(username, password);
    }

    @Override
    public void execute(String choice) {

    }
}
