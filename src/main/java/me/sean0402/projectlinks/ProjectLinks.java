package me.sean0402.projectlinks;

import me.sean0402.projectlinks.CommandBuilder.CommandLib;
import me.sean0402.projectlinks.Commands.LinksCommand;
import me.sean0402.projectlinks.Cooldowns.CooldownManager;
import me.sean0402.projectlinks.Listener.CommandListener;
import me.sean0402.projectlinks.Listener.PlayerQuit;
import me.sean0402.projectlinks.MenuBuilder.MenuHandler;
import me.sean0402.projectlinks.MenuBuilder.MenuUtility;
import me.sean0402.projectlinks.OOP.ComandOOP;
import me.sean0402.projectlinks.Vault.Vault;
import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 Created on 01/09/2020 at 19:09
 Author - Sean
*/
public class ProjectLinks extends JavaPlugin {

    private static ProjectLinks instance;
    private final Vault vault = new Vault();

    private static final Map<Player, MenuUtility> menuUtilityMap = new HashMap<>();
    private final CooldownManager manager = new CooldownManager();

    public static List<ComandOOP> commandList = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        if (!vault.setupEconomy()) {
            Utils.sendConsoleMessage("&cWARNING");
            Utils.sendConsoleMessage("&fVault not found! Please download an economy plugin to use the cost feature!");
        }
        saveDefaultConfig();
        loadCommands();
        registerListeners(new CommandListener(manager),
                new PlayerQuit(manager),
                new MenuHandler());
        new CommandLib().setupBukkit(this).register(new LinksCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    private void loadCommands() {
        ConfigurationSection section = getConfig().getConfigurationSection("Commands");
        if (section == null) return;
        section.getKeys(false).forEach(commands -> {
            commandList.add(new ComandOOP(commands,
                    section.getLong(commands + ".Cooldown"),
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

    public Vault getVault() {
        return vault;
    }

    public static MenuUtility getPlayerMenu(Player p) {
        MenuUtility menuUtility;

        if (menuUtilityMap.containsKey(p)) {
            return menuUtilityMap.get(p);
        } else {
            menuUtility = new MenuUtility(p);
            menuUtilityMap.put(p, menuUtility);
            return menuUtility;
        }
    }
}
