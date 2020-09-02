package me.sean0402.projectlinks.CommandBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.sean0402.projectlinks.CommandBuilder.register.BukkitRegister;
import me.sean0402.projectlinks.CommandBuilder.register.RegisterBase;

/*
 Created on 02/09/2020 at 01:53
 Author - Sean
*/
@NoArgsConstructor
public class CommandLib {

    @Getter
    private org.bukkit.plugin.Plugin bukkitPlugin;
    private RegisterBase platform;

    public CommandLib setupBukkit(org.bukkit.plugin.Plugin bukkitPlugin) {
        this.bukkitPlugin = bukkitPlugin;
        this.platform = new BukkitRegister(this);
        this.platform.setup();
        return this;
    }

    public CommandLib register(Command command) {
        command.setup();
        if (this.bukkitPlugin != null)
            this.platform.registerCommand(command, this.bukkitPlugin);
        return this;
    }

}
