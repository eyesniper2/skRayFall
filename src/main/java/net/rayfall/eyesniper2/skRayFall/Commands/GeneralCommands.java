package net.rayfall.eyesniper2.skRayFall.Commands;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GeneralCommands implements CommandExecutor{
	
	private final skRayFall plugin;
	 
	public GeneralCommands(skRayFall plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLable, String[] args){
		if (args.length == 0){
			return false;
		}
		else if (args.length == 1){
			if(args[0].toLowerCase().equals("reload")){
				plugin.reloadConfig();
				return true;
				}
			else
			return false;
			}
		
		
		return false;
		
	}
}
