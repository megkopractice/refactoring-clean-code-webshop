package org.example;

public class CommandHandler {
    public void invoke(Command command) {
        command.execute();
    }

}
