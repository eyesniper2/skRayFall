package net.rayfall.eyesniper2.skrayfall.generalexpressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

public class ExprSpecificEnchantIndex extends SimpleExpression<String> {

  // info of enchantment %number% on %itemstack%

  Expression<Number> index;
  Expression<ItemStack> item;

  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    index = (Expression<Number>) exp[0];
    item = (Expression<ItemStack>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected String[] get(Event evt) {
    if (item != null) {
      Object[] exp = item.getSingle(evt).getEnchantments().keySet().toArray();
      int finalIndex = (index.getSingle(evt).intValue() - 1);
      int length = (exp.length - 1);
      if (length >= finalIndex && finalIndex >= 0) {
        int level = item.getSingle(evt).getEnchantments().get(exp[finalIndex]);
        String name = ((Enchantment) exp[finalIndex]).getName();
        return new String[] {parseNamesNicely(name) + " " + level};
      }
      return null;
    }
    return null;
  }

  private String parseNamesNicely(String name) {
    switch (name) {
      case "DAMAGE_ALL":
        name = "Sharpness";
        break;
      case "DAMAGE_ARTHROPODS":
        name = "Bane of Arthropods";
        break;
      case "DAMAGE_UNDEAD":
        name = "Smite";
        break;
      case "DIG_SPEED":
        name = "Efficiency";
        break;
      case "DURABILITY":
        name = "Unbreaking";
        break;
      case "KNOCKBACK":
        name = "Knockback";
        break;
      case "LOOT_BONUS_BLOCKS":
        name = "Fortune";
        break;
      case "LOOT_BONUS_MOBS":
        name = "Looting";
        break;
      case "OXYGEN":
        name = "Respiration";
        break;
      case "PROTECTION_ENVIRONMENTAL":
        name = "Protection";
        break;
      case "PROTECTION_EXPLOSIONS":
        name = "Blast Protection";
        break;
      case "PROTECTION_FALL":
        name = "Feather Falling";
        break;
      case "PROTECTION_FIRE":
        name = "Fire Protection";
        break;
      case "PROTECTION_PROJECTILE":
        name = "Projectile Protection";
        break;
      case "SILK_TOUCH":
        name = "Silk Touch";
        break;
      case "WATER_WORKER":
        name = "Aqua Affinity";
        break;
      case "ARROW_FIRE":
        name = "Flame";
        break;
      case "ARROW_DAMAGE":
        name = "Power";
        break;
      case "ARROW_KNOCKBACK":
        name = "Punch";
        break;
      case "ARROW_INFINITE":
        name = "Infinity";
        break;
      case "THORNS":
        name = "Thorns";
        break;
      case "LURE":
        name = "Lure";
        break;
      case "LUCK":
        name = "Luck";
        break;
      case "DEPTH_STRIDER":
        name = "Depth Strider";
        break;
      default:
        break;
    }
    return name;
  }



}
