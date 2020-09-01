package me.sean0402.projectlinks.Listener;

import me.sean0402.projectlinks.Cooldowns.CooldownManager;
import me.sean0402.projectlinks.OOP.Command;
import me.sean0402.projectlinks.ProjectLinks;
import me.sean0402.projectlinks.utils.Utils;
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
        for (Command command : ProjectLinks.commandList) {
            if (message.equalsIgnoreCase(command.getCommandName())) {
                e.setCancelled(true);
                if (!manager.tryCooldown(e.getPlayer().getUniqueId(), command.getDelay())) {
                    e.getPlayer().sendMessage(Utils.colour(ProjectLinks.getInstance().getConfig().getString("Messages.onCooldown").replace("%remaining%", String.valueOf(manager.getTimeReamining(e.getPlayer())))));
                }
            }
        }
    }
}
