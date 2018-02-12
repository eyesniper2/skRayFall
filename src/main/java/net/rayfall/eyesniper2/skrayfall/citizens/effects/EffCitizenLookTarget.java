package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffCitizenLookTarget extends Effect {

  private Expression<Number> id;
  private Expression<Location> targetLoc;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    targetLoc = (Expression<Location>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npcLook = registry.getById(id.getSingle(evt).intValue());
    if (npcLook != null) {
      npcLook.faceLocation(targetLoc.getSingle(evt));
    }
  }

}
