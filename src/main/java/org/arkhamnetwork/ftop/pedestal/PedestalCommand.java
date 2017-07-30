package org.arkhamnetwork.ftop.pedestal;

import org.arkhamnetwork.ftop.FactionTop;
import org.arkhamnetwork.ftop.config.server.PedestalEntry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Matt on 2017-07-29.
 */
public class PedestalCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pedestal") && sender instanceof Player && sender.hasPermission("ftop.pedestal")) {


            Player player = (Player) sender;

            if (args.length == 2) {

                if (args[0].equalsIgnoreCase("set")) {

                    int pedNumber = 0;

                    try {
                        pedNumber = Integer.valueOf(args[1]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "That is not a valid number!");
                    }

                    FactionTop.getPedestalConfig().getPedestals().set(pedNumber, new PedestalEntry().fromLocation(player.getLocation()));
                    FactionTop.getInstance().reloadConfig();
                    player.sendMessage(ChatColor.GREEN + "Set pedestal " + pedNumber + " to your current location!");
                    return true;
                }
            }
        }
        return false;
    }
}
