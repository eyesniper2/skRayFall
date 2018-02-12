package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffGiveLookCloseTrait extends Effect {

  // give npc %number% the look close trait

  private Expression<Number> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npc = registry.getById(id.getSingle(evt).intValue());
    npc.addTrait(LookClose.class);
    npc.getTrait(LookClose.class).setRealisticLooking(true);
    npc.getTrait(LookClose.class).toggle();
  }

}
