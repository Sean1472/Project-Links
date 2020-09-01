package me.sean0402.projectlinks.Listener;

import me.sean0402.projectlinks.OOP.Command;
import me.sean0402.projectlinks.ProjectLinks;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/*
 Created on 01/09/2020 at 20:15
 Author - Sean
*/
public class CommandListener implements Listener {

    @EventHandler
    public void onCommandSend(AsyncPlayerChatEvent e) {
        for (Command command : ProjectLinks.commandList) {
            if (e.getMessage().equalsIgnoreCase(command.getCommandName())) {
                e.getPlayer().sendMessage("hi");
            }
        }
    }
}
