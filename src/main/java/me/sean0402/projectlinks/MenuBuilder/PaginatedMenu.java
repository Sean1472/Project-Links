package me.sean0402.projectlinks.MenuBuilder;

import me.sean0402.projectlinks.OOP.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/*
 Created on 02/09/2020 at 02:32
 Author - Sean
*/
public abstract class PaginatedMenu extends Menu {

    protected int page = 0;

    public abstract int getMaxPageItems();

    protected int index = 0;

    protected ItemStack FORWARD_BUTTON = new ItemBuilder(Material.ARROW).setName("&aForward page")
            .addStringData("forward").build();

    protected ItemStack BACK_BUTTON = new ItemBuilder(Material.ARROW).setName("&aBack a page")
            .addStringData("back").build();


    public void setForwardButton(int slot) {
        inventory.setItem(slot, FORWARD_BUTTON);
    }

    public void setBackButton(int slot) {
        inventory.setItem(slot, BACK_BUTTON);
    }
    public PaginatedMenu(MenuUtility utility) {
        super(utility);
    }
}
