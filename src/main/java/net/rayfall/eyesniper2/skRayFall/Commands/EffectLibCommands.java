package net.rayfall.eyesniper2.skRayFall.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.rayfall.eyesniper2.skRayFall.skRayFall;
import net.rayfall.eyesniper2.skRayFall.utli.YMLAddEffect;

public class EffectLibCommands implements CommandExecutor{
	
	private final skRayFall plugin;
	 
	public EffectLibCommands(skRayFall plugin) {
		this.plugin = plugin;
	}
	//Note arg[1] is the effect type and 2 is the user chosen name
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
			else if (args.length == 3){
				if (args[0].equalsIgnoreCase("createeffect")){
					YMLAddEffect.addEffect(args[1], args[2], sender);
					return true;
				}
				else
				return false;
			}
			else
			return false;
	}

}
