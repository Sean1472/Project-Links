package me.sean0402.projectlinks.OOP;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 01/09/2020 at 20:16
 Author - Sean
*/
public class Command {

    private final String commandName;
    private final long cooldown;
    private final double cost;
    private final List<String> actions;

    public Command(String commandName, long cooldown, double cost, List<String> commands) {
        this.commandName = commandName != null ? commandName : "Command invalid.";
        this.cooldown = cooldown;
        this.cost = cost;
        this.actions = commands != null ? commands : new ArrayList<>();
    }

    public String getCommandName() {
        return commandName;
    }

    public long getCooldown() {
        return cooldown;
    }

    public double getCost() {
        return cost;
    }

    public List<String> getActions() {
        return actions;
    }
}
