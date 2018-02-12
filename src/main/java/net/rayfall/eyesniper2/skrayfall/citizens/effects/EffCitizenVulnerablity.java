package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffCitizenVulnerablity extends Effect {

  private Expression<Number> id;
  private boolean vun;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int matchedPattern, Kleenean arg2, ParseResult result) {
    if (result.mark == 1) {
      // invun
      vun = false;
    } else {
      vun = true;
    }
    id = (Expression<Number>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event evt, boolean arg1) {
    return "NPC id: " + id.getSingle(evt).intValue() + "Is vun? " + vun;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npcName = registry.getById(id.getSingle(evt).intValue());
    if (vun == true) {
      npcName.setProtected(false);
    }
    if (vun == false) {
      npcName.setProtected(true);
    }
  }
}
