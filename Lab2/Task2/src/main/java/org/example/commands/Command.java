package org.example.commands;

import org.example.CommandExecutor;

public class Command {
    private final String name;
    private final CommandExecutor executor;

    public Command(String name, CommandExecutor executor){
        this.name = name;
        this.executor = executor;
    }

    public String getName() {
        return name;
    }

    public CommandExecutor getExecutor(){
        return executor;
    }
}
