package me.sean0402.projectlinks.MenuBuilder;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

/*
 Created on 02/09/2020 at 02:26
 Author - Sean
*/
public class MenuHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    private void onMenuClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        final InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu) {
            if (e.getClickedInventory() == p.getOpenInventory().getBottomInventory()) {
                e.setCancelled(true);
                return;
            }

            if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                e.setCancelled(true);
            }
            e.setCancelled(true);
            if (e.getCurrentItem() == null) return;
            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }
    }
}
