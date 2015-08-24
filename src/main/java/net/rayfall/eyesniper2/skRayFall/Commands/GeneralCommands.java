package net.rayfall.eyesniper2.skRayFall.Commands;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GeneralCommands implements CommandExecutor{
	
	private final skRayFall plugin;
	private String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "skRayFall" + ChatColor.GRAY + "] " + ChatColor.RESET; 
	 
	public GeneralCommands(skRayFall plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLable, String[] args){
		if (args.length == 0){
			return false;
		}
		else if (args.length == 1){
			if(args[0].toLowerCase().equals("reload")){
				if(sender.hasPermission("skrayfall.reload")){
					plugin.reloadConfig();
					plugin.enableFastScoreboards = plugin.getConfig().getBoolean("enableFastScoreBoards");
					sender.sendMessage(prefix + ChatColor.GREEN + "The configuration file has been reloaded.");
					return true;
				}
				else{
					sender.sendMessage(prefix + ChatColor.RED + "You do not have the permission: " + ChatColor.GOLD + "skrayfall.reload" + ChatColor.RED + "!");
					return true;
				}
			}
			else
			return false;
			}
		else
		return false;
		
	}
}
