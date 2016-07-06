package net.rayfall.eyesniper2.skrayfall.teams;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffDeleteTeam extends Effect {

  // (delete|remove) [a] team named %string%

  private Expression<String> team;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    team = (Expression<String>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (team != null) {
      Core.teamManager.removeTeam(team.getSingle(evt).replace("\"", ""));
    }

  }

}
