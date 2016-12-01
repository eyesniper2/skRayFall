package net.rayfall.eyesniper2.skrayfall.teams;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffSetTeamDisplayName extends Effect {

  //set team display names for %string% to %string%

  private Expression<String> teamName;
  private Expression<String> newName;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    teamName = (Expression<String>) exp[0];
    newName = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    TeamManager tm = Core.teamManager;
    if (teamName != null && newName != null) {
      tm.setDisplayName(teamName.getSingle(evt).replace("\"", ""),
          newName.getSingle(evt).replace("\"", ""));
    }
  }
}
