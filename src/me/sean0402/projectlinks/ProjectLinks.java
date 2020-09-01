package me.sean0402.projectlinks;

import me.sean0402.projectlinks.Listener.CommandListener;
import me.sean0402.projectlinks.OOP.Command;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 01/09/2020 at 19:09
 Author - Sean
*/
public class ProjectLinks extends JavaPlugin {

    private static ProjectLinks instance;

    public static List<Command> commandList = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        loadCommands();
        registerListeners(new CommandListener());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadCommands() {
        ConfigurationSection section = getConfig().getConfigurationSection("Commands");
        if (section == null) return;
        section.getKeys(false).forEach(commands -> {
            commandList.add(new Command(commands,
                    section.getLong(commands + ".Delay"),
                    section.getDouble(commands + ".Cost"),
                    section.getStringList(commands + ".Actions")));
        });
    }

    private void registerListeners(Listener... listeners) {
        for (Listener lis : listeners) Bukkit.getPluginManager().registerEvents(lis, this);
    }


    public static ProjectLinks getInstance() {
        return instance;
    }
}
