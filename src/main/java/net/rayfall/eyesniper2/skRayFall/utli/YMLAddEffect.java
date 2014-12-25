package net.rayfall.eyesniper2.skRayFall.utli;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import ch.njol.skript.Skript;
import net.rayfall.eyesniper2.skRayFall.skRayFall;

public class YMLAddEffect extends skRayFall{
	
	public static void addEffect(String holdType, String holdName, CommandSender sender){
		//Check to make sure name doesn't already exist
		String name = holdName.toLowerCase();
		String type = holdType.toLowerCase();
		switch(type){
		case "atom":
			skRayFall.plugin.getConfig().set(name, null);
			skRayFall.plugin.getConfig().set(name + ".type", type.toLowerCase());
			skRayFall.plugin.getConfig().set(name + ".particle nucleus", "drip water");
			skRayFall.plugin.getConfig().set(name + ".particle orbital", "drip lava");
			skRayFall.plugin.getConfig().set(name + ".radius", 3);
			skRayFall.plugin.getConfig().set(name + ".radius nucleus", 0.2);
			skRayFall.plugin.getConfig().set(name + ".particles nucleus", 10);
			skRayFall.plugin.getConfig().set(name + ".particles orbital", 10);
			skRayFall.plugin.getConfig().set(name + ".orbitals", 3);
			skRayFall.plugin.getConfig().set(name + ".rotation", 0);
			skRayFall.plugin.getConfig().set(name + ".angular velocity", Math.PI/80);
			skRayFall.plugin.saveConfig();
			sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "skRayFall" + ChatColor.GRAY + "]" + ChatColor.GREEN + " The effect " + type + " with the name " + name + " Has been created!");
			break;
		default:
			Skript.error("That effect type does not exists!");
		}
		
	}

}
