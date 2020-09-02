package me.sean0402.projectlinks.MenuBuilder;

import me.sean0402.projectlinks.OOP.ItemBuilder;
import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/*
 Created on 02/09/2020 at 02:15
 Author - Sean
*/
public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;

    protected MenuUtility menuUtility;

    protected List<Integer> sideSlots = new ArrayList<>();

    protected ItemStack FILLER_ITEM = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("").build();
    protected ItemStack CLOSE_BUTTON = new ItemBuilder(Material.COMPASS).setName("&7Close menu").build();

    public Menu(MenuUtility menuUtility) {
        this.menuUtility = menuUtility;
    }

    public void setCloseButton(int slot) {
        inventory.setItem(slot, CLOSE_BUTTON);
    }

    public Player getPlayer() {
        return menuUtility.getPlayer();
    }

    public void fillAll(ItemStack itemStack) {
        for (int i = 0; i < inventory.getSize(); i++)
            if (inventory.getItem(i) == null)
                inventory.setItem(i, itemStack);
    }

    public void fillBorder(ItemStack itemStack) {
        int size = inventory.getSize();
        int rows = size / 9;

        if (rows >= 3) {
            for (int i = 0; i <= 8; i++) {
                inventory.setItem(i, itemStack);
                sideSlots.add(i);
            }

            for (int s = 8; s < (inventory.getSize() - 9); s += 9) {
                int lastSlot = s + 1;
                inventory.setItem(s, itemStack);
                inventory.setItem(lastSlot, itemStack);
                sideSlots.add(s);
                sideSlots.add(lastSlot);
            }

            for (int lr = (inventory.getSize() - 9); lr < inventory.getSize(); lr++) {
                inventory.setItem(lr, itemStack);
                sideSlots.add(lr);
            }
        }
    }

    public abstract String getMenuName();

    public abstract int getSlots();

    public abstract void handleMenu(InventoryClickEvent e);

    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), Utils.colour(getMenuName()));
        this.setMenuItems();
        menuUtility.getPlayer().openInventory(inventory);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
