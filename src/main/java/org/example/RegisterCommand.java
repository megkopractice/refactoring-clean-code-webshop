package org.example;

public class RegisterCommand implements Command{
    private WebShop webShop;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String address;
    private String phoneNumber;

    public RegisterCommand(WebShop webShop, String username, String password, String firstName, String lastName, String email, int age, String address, String phoneNumber)
    {
        this.webShop = webShop;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void execute() {
        webShop.register(username, password, firstName, lastName, email, age, address, phoneNumber);
    }
}
