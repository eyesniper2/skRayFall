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

public class EffSetCitizenName extends Effect {

  private Expression<Number> idNum;
  private Expression<String> name;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    idNum = (Expression<Number>) exp[0];
    name = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event evt, boolean arg1) {
    return "NPC " + idNum.getSingle(evt).intValue() + " Name changed to " + name.getSingle(evt);
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npc = registry.getById(idNum.getSingle(evt).intValue());
    npc.setName(name.getSingle(evt).toString().replace("\"", ""));
  }

}
