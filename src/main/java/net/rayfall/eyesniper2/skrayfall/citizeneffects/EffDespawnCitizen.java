package net.rayfall.eyesniper2.skrayfall.citizeneffects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffDespawnCitizen extends Effect {

  private Expression<Number> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return "Despawn NPC with ID: " + id.getSingle(arg0).intValue();
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    if (registry.getById(id.getSingle(evt).intValue()) != null) {
      try {
        NPC despawn = registry.getById(id.getSingle(evt).intValue());
        despawn.despawn(null);
      } catch (NullPointerException exp) {
        return;
      }
    }

  }

}
