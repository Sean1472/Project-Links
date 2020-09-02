package me.sean0402.projectlinks.Menus;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.sean0402.projectlinks.MenuBuilder.MenuUtility;
import me.sean0402.projectlinks.MenuBuilder.PaginatedMenu;
import me.sean0402.projectlinks.OOP.ComandOOP;
import me.sean0402.projectlinks.OOP.ItemBuilder;
import me.sean0402.projectlinks.ProjectLinks;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

/*
 Created on 02/09/2020 at 02:32
 Author - Sean
*/
public class MainMenu extends PaginatedMenu {


    public MainMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public int getMaxPageItems() {
        return 7;
    }

    @Override
    public String getMenuName() {
        return "Project Link - Main Menu";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        NBTItem nbtItem = new NBTItem(e.getCurrentItem());
        if (e.getCurrentItem().getType() == Material.COMPASS) {
            e.getWhoClicked().closeInventory();
        } else if (e.getCurrentItem().getType() == Material.ARROW) {
            if (nbtItem.hasKey("forward")) {
                if (!((index + 1) >= getRegisteredCommands().size())) {
                    page = page + 1;
                    super.open();
                    ;
                }
            } else if (nbtItem.hasKey("back")) {
                if (page == 0) return;
                else {
                    page = page - 1;
                    super.open();
                }
            }
        }
    }

    @Override
    public void setMenuItems() {
        fillBorder(FILLER_ITEM);
        setBackButton(21);
        setCloseButton(22);
        setForwardButton(23);

        if (!getRegisteredCommands().isEmpty()) {
            for (int i = 0; i < getMaxPageItems(); i++) {
                index = getMaxPageItems() * page + i;
                if (index >= getRegisteredCommands().size()) break;
                if (getRegisteredCommands().get(index) != null) {
                    ItemStack commandItemstack = new ItemBuilder(getRegisteredCommands().get(index).getCommandItem())
                            .setAmount(index >= 64 ? 64 : index + 1).build();
                    inventory.addItem(commandItemstack);
                }
            }
        }
    }

    private ArrayList<ComandOOP> getRegisteredCommands() {
        return (ArrayList<ComandOOP>) ProjectLinks.commandList;
    }
}
