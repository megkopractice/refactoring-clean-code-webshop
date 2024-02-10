package org.example;
/**
 * CommandHandler class
 * This class gathers all the commands and invokes them in the main method
 * This class is part of the Command Pattern
 *
 */
public class CommandHandler {
    public void invoke(Command command) {
        command.execute();
    }

}
