package me.sean0402.projectlinks.CommandBuilder.register;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.sean0402.projectlinks.CommandBuilder.Command;
import me.sean0402.projectlinks.CommandBuilder.CommandLib;

/*
 Created on 02/09/2020 at 02:10
 Author - Sean
*/
@AllArgsConstructor
public abstract class RegisterBase {

    @Getter
    protected CommandLib commandLib;

    public abstract void setup();

    public CommandLib registerCommand(Command command, org.bukkit.plugin.Plugin plugin) {
        return this.commandLib;
    }
}
