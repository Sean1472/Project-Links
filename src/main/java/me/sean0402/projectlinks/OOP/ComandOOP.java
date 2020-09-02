package me.sean0402.projectlinks.OOP;

import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 01/09/2020 at 20:16
 Author - Sean
*/
public class ComandOOP {

    private final String commandName;
    private final long cooldown;
    private final double cost;
    private final List<String> actions;

    public ComandOOP(String commandName, long cooldown, double cost, List<String> commands) {
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

    public ItemStack getCommandItem() {
        ItemStack item = new ItemBuilder(Material.BOOK)
                .setName("&aCommand &f(&a" + getCommandName() + "&f)")
                .setLore(getLoreLines().split("\\n"))
                .addLoreLine("&7&nActions:", 0)
                .addLoreLine("&7&nInformation", "&fCooldown: &a" + getCooldown(), "&fCost: &a" + getCost())
                .build();
        return item.clone();
    }

    public String getLoreLines() {
        StringBuilder result = new StringBuilder();
        for (String s : getActions()) {
            result.append(Utils.colour("&7- &a")).append(s).append("\n");
        }
        return result.toString();
    }
}
