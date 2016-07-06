package net.rayfall.eyesniper2.skrayfall.teams;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffCreateTeam extends Effect {

  // (create|make) [a] team named %string% [with %players%]

  private Expression<String> name;
  private Expression<Player> players;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    name = (Expression<String>) exp[0];
    players = (Expression<Player>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    TeamManager tm = Core.teamManager;
    if (name != null) {
      String tname = name.getSingle(evt).replace("\"", "");
      if (players != null) {
        tm.createTeam(tname, players.getAll(evt));
      } else {
        tm.createTeam(tname);
      }
    }

  }

}
