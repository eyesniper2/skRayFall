package net.rayfall.eyesniper2.skrayfall.utli;

import ch.njol.skript.Skript;
import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class YmlAddEffect extends Core {

  /**
   * An experimental method to create EffectLib effects and generate their options into a yml file.
   * 
   * @param holdType The EffectLib effect type.
   * @param holdName The unique name of the effect.
   * @param sender The command sender of the original command.
   */
  @Deprecated
  public static void addEffect(String holdType, String holdName, CommandSender sender) {
    // Check to make sure name doesn't already exist
    String name = holdName.toLowerCase();
    String type = holdType.toLowerCase();
    switch (type) {
      case "atom":
        Core.plugin.getConfig().set(name, null);
        Core.plugin.getConfig().set(name + ".type", type.toLowerCase());
        Core.plugin.getConfig().set(name + ".particle nucleus", "drip water");
        Core.plugin.getConfig().set(name + ".particle orbital", "drip lava");
        Core.plugin.getConfig().set(name + ".radius", 3);
        Core.plugin.getConfig().set(name + ".radius nucleus", 0.2);
        Core.plugin.getConfig().set(name + ".particles nucleus", 10);
        Core.plugin.getConfig().set(name + ".particles orbital", 10);
        Core.plugin.getConfig().set(name + ".orbitals", 3);
        Core.plugin.getConfig().set(name + ".rotation", 0);
        Core.plugin.getConfig().set(name + ".angular velocity", Math.PI / 80);
        Core.plugin.saveConfig();
        sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "skRayFall" + ChatColor.GRAY
            + "]" + ChatColor.GREEN + " The effect " + type + " with the name " + name
            + " Has been created!");
        break;
      default:
        Skript.error("That effect type does not exists!");
    }

  }

}
