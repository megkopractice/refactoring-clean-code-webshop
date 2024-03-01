package org.example;
public class SetPasswordActionCommand implements Command{

        public WebShop webShop;

        public SetPasswordActionCommand(WebShop webShop)
        {
            this.webShop = webShop;
        }

        @Override
        public void execute() {
            webShop.setPassword();
        }
}
