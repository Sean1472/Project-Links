package me.sean0402.projectlinks.MenuBuilder;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

/*
 Created on 02/09/2020 at 02:18
 Author - Sean
*/
public class MenuUtility {

    @Getter
    @Setter
    private Player player;

    public MenuUtility(Player player) {
        this.player = player;
    }
}
