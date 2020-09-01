package me.sean0402.projectlinks.Listener;

import me.sean0402.projectlinks.Cooldowns.CooldownManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/*
 Created on 01/09/2020 at 21:15
 Author - Sean
*/
public class PlayerQuit implements Listener {

    private final CooldownManager cooldownManager;

    public PlayerQuit(CooldownManager manager) {
        this.cooldownManager = manager;
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        cooldownManager.removeCooldown(p.getUniqueId());
    }
}
