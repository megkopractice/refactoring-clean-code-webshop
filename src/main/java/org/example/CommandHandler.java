package org.example;
/**
 * CommandHandler class
 * This class gathers all sorts of the commands and invokes them in the Main class
 * This class is part of the Command Pattern
 *
 */
public class CommandHandler {
    public void invoke(Command command) {
        command.execute();
    }

}
