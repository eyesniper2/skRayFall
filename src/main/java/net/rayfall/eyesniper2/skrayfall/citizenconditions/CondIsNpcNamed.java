package net.rayfall.eyesniper2.skrayfall.citizenconditions;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.event.NPCEvent;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class CondIsNpcNamed extends Condition {

  // npc/citizen['s] [is] name[d] [is] "%string%"

  private Expression<String> name;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    name = (Expression<String>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  public boolean check(Event evt) {
    if (evt instanceof NPCEvent) {
      NPC testTarget = ((NPCEvent) evt).getNPC();
      if (testTarget == null) {
        return false;
      }
      if (name.getSingle(evt).toString().replace("\"", "").trim()
          .equals(testTarget.getFullName())) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

}
