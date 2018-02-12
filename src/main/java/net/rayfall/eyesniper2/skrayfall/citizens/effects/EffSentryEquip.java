package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

public class EffSentryEquip extends Effect {

  // (equip|give) citizen %number% with [an] %itemstack%
  @SuppressWarnings("unused")
  private Expression<ItemStack> item;
  private Expression<Number> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    item = (Expression<ItemStack>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    @SuppressWarnings("unused")
    NPC npc = registry.getById(id.getSingle(evt).intValue());
  }

}
