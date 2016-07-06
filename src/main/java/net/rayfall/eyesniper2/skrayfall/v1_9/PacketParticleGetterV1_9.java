package net.rayfall.eyesniper2.skrayfall.v1_9;

import ch.njol.skript.Skript;

import net.minecraft.server.v1_9_R1.EnumParticle;

public class PacketParticleGetterV1_9 {
  
  /**
   * Converts a string to a EnumParticle.
   * 
   * @param testString String to be converted
   */
  public static EnumParticle get(String testString) {
    switch (testString.replace("\"", "").replace(" ", "_").toUpperCase()) {
      case "BARRIER":
        return EnumParticle.BARRIER;
      case "BLOCK_CRACK":
        return EnumParticle.BLOCK_CRACK;
      case "BLOCK_DUST":
        return EnumParticle.BLOCK_DUST;
      case "CLOUD":
        return EnumParticle.CLOUD;
      case "CRIT":
        return EnumParticle.CRIT;
      case "CRIT_MAGIC":
        return EnumParticle.CRIT_MAGIC;
      case "DRIP_LAVA":
        return EnumParticle.DRIP_LAVA;
      case "DRIP_WATER":
        return EnumParticle.DRIP_WATER;
      case "ENCHANTMENT_TABLE":
        return EnumParticle.ENCHANTMENT_TABLE;
      case "EXPLOSION_HUGE":
        return EnumParticle.EXPLOSION_HUGE;
      case "EXPLOSION_LARGE":
        return EnumParticle.EXPLOSION_LARGE;
      case "EXPLOSION_NORMAL":
        return EnumParticle.EXPLOSION_NORMAL;
      case "FIREWORKS_SPARK":
        return EnumParticle.FIREWORKS_SPARK;
      case "FLAME":
        return EnumParticle.FLAME;
      case "FOOTSTEP":
        return EnumParticle.FOOTSTEP;
      case "HEART":
        return EnumParticle.HEART;
      case "ITEM_CRACK":
        return EnumParticle.ITEM_CRACK;
      case "ITEM_TAKE":
        return EnumParticle.ITEM_TAKE;
      case "LAVA":
        return EnumParticle.LAVA;
      case "MOB_APPEARANCE":
        return EnumParticle.MOB_APPEARANCE;
      case "NOTE":
        return EnumParticle.NOTE;
      case "PORTAL":
        return EnumParticle.PORTAL;
      case "REDSTONE":
        return EnumParticle.REDSTONE;
      case "SLIME":
        return EnumParticle.SLIME;
      case "SMOKE_LARGE":
        return EnumParticle.SMOKE_LARGE;
      case "SMOKE_NORMAL":
        return EnumParticle.SMOKE_NORMAL;
      case "SNOW_SHOVEL":
        return EnumParticle.SNOW_SHOVEL;
      case "SNOWBALL":
        return EnumParticle.SNOWBALL;
      case "SPELL":
        return EnumParticle.SPELL;
      case "SPELL_INSTANT":
        return EnumParticle.SPELL_INSTANT;
      case "SPELL_MOB":
        return EnumParticle.SPELL_MOB;
      case "SPELL_MOB_AMBIENT":
        return EnumParticle.SPELL_MOB_AMBIENT;
      case "SPELL_WITCH":
        return EnumParticle.SPELL_WITCH;
      case "SUSPENDED":
        return EnumParticle.SUSPENDED;
      case "SUSPENDED_DEPTH":
        return EnumParticle.SUSPENDED_DEPTH;
      case "TOWN_AURA":
        return EnumParticle.TOWN_AURA;
      case "VILLAGER_ANGRY":
        return EnumParticle.VILLAGER_ANGRY;
      case "VILLAGER_HAPPY":
        return EnumParticle.VILLAGER_HAPPY;
      case "WATER_BUBBLE":
        return EnumParticle.WATER_BUBBLE;
      case "WATER_DROP":
        return EnumParticle.WATER_DROP;
      case "WATER_SPLASH":
        return EnumParticle.WATER_SPLASH;
      case "WATER_WAKE":
        return EnumParticle.WATER_WAKE;
      default:
        Skript.error("Invalid particle type selected! Using a Crit instead.");
        return EnumParticle.CRIT;
    }
  }
}
