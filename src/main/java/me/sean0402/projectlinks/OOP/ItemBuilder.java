package me.sean0402.projectlinks.OOP;

import de.tr7zw.changeme.nbtapi.NBTItem;
import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/*
 Created on 02/09/2020 at 02:15
 Author - Sean
*/
public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this(new ItemStack(material, 1));
    }

    public ItemBuilder(Material material, int amount, short data) {
        this(new ItemStack(material, amount, data));
    }

    public ItemBuilder(ItemStack item) {
        itemStack = item;
        itemMeta = item.getItemMeta();
    }

    public ItemBuilder setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(Utils.colour(name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        itemMeta.setLore(Utils.colorMessage(Arrays.asList(lore)));
        return this;
    }

    /*public ItemBuilder setLore(List<String> lore) {
        for (String s : lore) {
            itemMeta.setLore(Utils.colorMessage(Arrays.asList(s.split("\n"))));
        }
        return this;
    }*/

    public ItemBuilder addStringData(String value) {
        NBTItem nbtItem = new NBTItem(itemStack);
        nbtItem.setString(value, null);
        return this;
    }

    public ItemStack build() {
        itemStack.setItemMeta(itemMeta);
        return itemStack.clone();
    }
}
