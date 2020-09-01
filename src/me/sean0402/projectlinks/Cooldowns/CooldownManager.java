package me.sean0402.projectlinks.Cooldowns;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 Created on 01/09/2020 at 20:53
 Author - Sean
*/
public class CooldownManager {

    private Map<String, Long> cooldowns = new HashMap<>();

    public long getCooldown(UUID uuid) {
        return calculateTimeRemaining(cooldowns.get(uuid.toString()));
    }

    private long setCooldown(UUID uuid, long delay) {
        return calculateTimeRemaining(cooldowns.put(uuid.toString(), System.currentTimeMillis() + (delay * 1000)));
    }

    public boolean tryCooldown(UUID uuid, long delay) {
        if (getCooldown(uuid) / 1000 > 0) {
            return false;
        }
        setCooldown(uuid, delay + 1);
        return true;
    }

    public void removeCooldown(UUID uuid) {
        cooldowns.remove(uuid.toString());
    }

    public long getTimeReamining(Player player) {
        return (getCooldown(player.getUniqueId()) / 1000);
    }

    private long calculateTimeRemaining(Long expireTime) {
        return expireTime != null ? expireTime - System.currentTimeMillis() : Long.MIN_VALUE;
    }


}
