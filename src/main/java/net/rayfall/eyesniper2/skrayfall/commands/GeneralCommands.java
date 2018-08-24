package net.rayfall.eyesniper2.skrayfall.commands;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GeneralCommands implements CommandExecutor {

    private final Core plugin;
    private String prefix =
            ChatColor.GRAY + "[" + ChatColor.BLUE + "skRayFall" + ChatColor.GRAY + "] " + ChatColor.RESET;

    public GeneralCommands(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLable,
                             String[] args) {
        if (args.length == 0) {
            return false;
        } else if (args.length == 1) {
            if (args[0].toLowerCase().equals("reload")) {
                if (sender.hasPermission("skrayfall.reload")) {
                    plugin.reloadConfig();
                    plugin.enableFastScoreboards = plugin.getConfig().getBoolean("enableFastScoreBoards");
                    sender
                            .sendMessage(prefix + ChatColor.GREEN + "The configuration file has been reloaded.");
                    return true;
                } else {
                    sender.sendMessage(prefix + ChatColor.RED + "You do not have the permission: "
                            + ChatColor.GOLD + "skrayfall.reload" + ChatColor.RED + "!");
                    return true;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}
