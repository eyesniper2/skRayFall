package net.rayfall.eyesniper2.skrayfall.utli;

import org.bukkit.entity.EntityType;

public class ScrubEntityType {
  
  /**
   * Convert a string to a bukkit entity type.
   * 
   * @param exprs The string to be converted.
   */
  public static EntityType getType(String exprs) {
    switch (exprs.replace("\"", "").toLowerCase().replace("_", " ").replaceFirst("the ", "")) {
      case "player":
      case "the player":
        return EntityType.PLAYER;
      case "pig":
        return EntityType.PIG;
      case "blaze":
        return EntityType.BLAZE;
      case "bat":
        return EntityType.BAT;
      case "chicken":
        return EntityType.CHICKEN;
      case "creeper":
        return EntityType.CREEPER;
      case "cow":
        return EntityType.COW;
      case "enderman":
        return EntityType.ENDERMAN;
      case "ender dragon":
        return EntityType.ENDER_DRAGON;
      case "ghast":
        return EntityType.GHAST;
      case "giant":
        return EntityType.GIANT;
      case "iron golem":
        return EntityType.IRON_GOLEM;
      case "magma cube":
        return EntityType.MAGMA_CUBE;
      case "mushroom cow":
        return EntityType.MUSHROOM_COW;
      case "ocelot":
        return EntityType.OCELOT;
      case "pig zombie":
        return EntityType.PIG_ZOMBIE;
      case "sheep":
        return EntityType.SHEEP;
      case "silverfish":
        return EntityType.SILVERFISH;
      case "squid":
        return EntityType.SQUID;
      case "snowman":
        return EntityType.SNOWMAN;
      case "wolf":
        return EntityType.WOLF;
      case "skeleton":
        return EntityType.SKELETON;
      case "slime":
        return EntityType.SLIME;
      case "spider":
        return EntityType.SPIDER;
      case "witch":
        return EntityType.WITCH;
      case "wither":
        return EntityType.WITHER;
      case "villager":
        return EntityType.VILLAGER;
      case "zombie":
        return EntityType.ZOMBIE;
      case "armor stand":
        return EntityType.ARMOR_STAND;
      case "guardian":
        return EntityType.GUARDIAN;
      default:
        return EntityType.PLAYER;
    }
  }
}
