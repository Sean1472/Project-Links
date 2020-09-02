package me.sean0402.projectlinks.CommandBuilder;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/*
 Created on 02/09/2020 at 02:10
 Author - Sean
*/
public abstract class Command {

    public void setup() {
    }

    public void execute(org.bukkit.command.CommandSender sender, String... args) {

    }

    public BukkitRunnable executeAsync(org.bukkit.command.CommandSender sender, String... args) {
        return new BukkitRunnable() {
            @Override
            public void run() {
            }
        };
    }

    public ArrayList<String> tabComplete(org.bukkit.command.CommandSender sender, String alias, String... args) {
        if (args.length <= 1 && !this.subCommands.isEmpty()) {
            if (args[0].isEmpty()) {
                return this.tabSubCommands();
            } else {
                return (this.tabSubCommands()
                        .stream()
                        .filter(item -> item.toLowerCase().startsWith(args[0]))
                        .collect(Collectors.toCollection(ArrayList::new))
                );
            }
        } else if (args.length > 1) {
            String command = args[0];

            if (this.containsSub(command)) {
                String[] lessArgs = Arrays.copyOfRange(args, 1, args.length);
                return this.subCommands.get(command).tabComplete(sender, alias, lessArgs);
            }
        }

        return new ArrayList<>();
    }

    private Map<String, Command> subCommands = new HashMap<>();

    public void sub(String name, Command command) {
        this.subCommands.put(name.toLowerCase(), command);
    }

    public boolean containsSub(String name) {
        return this.subCommands.containsKey(name);
    }

    public void processSubCommands(Player player, String[] args, Command help, Plugin instance) {

    }

    public ArrayList<String> tabSubCommands() {
        return new ArrayList<>(this.subCommands.keySet());
    }
}
