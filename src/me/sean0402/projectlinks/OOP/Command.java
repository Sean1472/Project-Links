package me.sean0402.projectlinks.OOP;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 01/09/2020 at 20:16
 Author - Sean
*/
public class Command {

    private final String commandName;
    private final long delay;
    private final double cost;
    private final List<String> actions;

    public Command(String commandName, long delay, double cost, List<String> commands) {
        this.commandName = commandName != null ? commandName : "Command invalid.";
        this.delay = delay;
        this.cost = cost;
        this.actions = commands != null ? commands : new ArrayList<>();
    }

    public String getCommandName() {
        return commandName;
    }

    public long getDelay() {
        return delay;
    }

    public double getCost() {
        return cost;
    }

    public List<String> getActions() {
        return actions;
    }
}
