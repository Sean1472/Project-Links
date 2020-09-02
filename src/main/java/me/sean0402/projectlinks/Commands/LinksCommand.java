package me.sean0402.projectlinks.Commands;

import me.sean0402.projectlinks.CommandBuilder.Command;
import me.sean0402.projectlinks.CommandBuilder.DynamicCommand;
import me.sean0402.projectlinks.Menus.MainMenu;
import me.sean0402.projectlinks.ProjectLinks;
import me.sean0402.projectlinks.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 Created on 02/09/2020 at 02:30
 Author - Sean
*/
@DynamicCommand(
        name = "link",
        aliases = {"links,projectlink,projectlinks"},
        description = "Main ProjectLinks command")
public class LinksCommand extends Command {

    @Override
    public void execute(CommandSender sender, String... args) {
        Player p = (Player) sender;
        if (args.length == 0 && (p.hasPermission("projectlinks.help"))) {
            sendHelp(p);
        } else if (args[0].equalsIgnoreCase("reload") && (p.hasPermission("projectlinks.reload"))) {
            ProjectLinks.getInstance().reloadConfig();
            p.sendMessage(Utils.colour(ProjectLinks.getInstance().getConfig().getString("Messages.reloadConfig")));
        } else if (args[0].equalsIgnoreCase("menu")) {
            new MainMenu(ProjectLinks.getPlayerMenu(p)).open();
        }
    }

    private void sendHelp(Player player) {
        player.sendMessage(Utils.colour("&8[]=====================[]"));
        player.sendMessage(Utils.colour(""));
        player.sendMessage(Utils.colour("&a/link &7- Help"));
        player.sendMessage(Utils.colour("&a/link menu &7- Manage commands"));
        player.sendMessage(Utils.colour(""));
        player.sendMessage(Utils.colour("&8[]=====================[]"));

    }


}
