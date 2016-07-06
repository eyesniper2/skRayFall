package net.rayfall.eyesniper2.skrayfall.teams;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffSetTeamPrefix extends Effect {

  // set prefix of team %string% to %string%

  private Expression<String> team;
  private Expression<String> pre;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    team = (Expression<String>) exp[0];
    pre = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (team != null && pre != null) {
      String prefix = pre.getSingle(evt).replace("\"", "");
      if (prefix.length() <= 16) {
        Core.teamManager.setPrefix(team.getSingle(evt).replace("\"", ""), prefix);
      } else {
        Skript.error(
            "Prefix can not be greater than 16 characters. It's currently " + prefix.length());
      }
    }

  }

}
