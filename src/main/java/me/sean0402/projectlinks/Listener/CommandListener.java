package me.sean0402.projectlinks.Listener;

import me.sean0402.projectlinks.Cooldowns.CooldownManager;
import me.sean0402.projectlinks.OOP.ComandOOP;
import me.sean0402.projectlinks.ProjectLinks;
import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/*
 Created on 01/09/2020 at 20:15
 Author - Sean
*/
public class CommandListener implements Listener {

    private final CooldownManager manager;

    public CommandListener(CooldownManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onCommandSend(PlayerCommandPreprocessEvent e) {
        String message = e.getMessage().replace("/", "");
        for (ComandOOP command : ProjectLinks.commandList) {
            if (message.equalsIgnoreCase(command.getCommandName())) {
                e.setCancelled(true);
                if (!manager.tryCooldown(e.getPlayer().getUniqueId(), command.getCooldown())) {
                    e.getPlayer().sendMessage(Utils.colour(ProjectLinks.getInstance().getConfig().getString("Messages.onCooldown").replace("%remaining%", String.valueOf(manager.getTimeReamining(e.getPlayer())))));
                    return;
                }
                runActions(e.getPlayer(), command);
                ProjectLinks.getInstance().getVault().getEcon().withdrawPlayer(e.getPlayer(), command.getCost());
                e.getPlayer().sendMessage(Utils.colour(ProjectLinks.getInstance().getConfig().getString("Messages.takenMoney").replace("%price%", String.valueOf(command.getCost()))));
            }
        }
    }

    private void runActions(Player player, ComandOOP command) {
        for (String prefix : command.getActions()) {
            String action = prefix.substring(prefix.indexOf("]") + 1).replace("%player%", player.getName()).replace("%Player%", player.getName()).trim();
            if (prefix.startsWith("[sendMessage]")) {
                player.sendMessage(Utils.colour(action));
            } else if (prefix.startsWith("[broadcast]")) {
                Bukkit.broadcastMessage(Utils.colour(action));
            } else if (prefix.startsWith("[player]")) {
                Bukkit.dispatchCommand(player, action);
            } else if (prefix.startsWith("[console]")) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), action);
            } else if (prefix.startsWith("[sound]")) {
                try {
                    player.playSound(player.getLocation(), Sound.valueOf(action), 1f, 1f);
                } catch (Exception e) {
                    Utils.sendConsoleMessage("&c" + action + " &cis not a valid sound!");
                }
            }
        }
    }
}
