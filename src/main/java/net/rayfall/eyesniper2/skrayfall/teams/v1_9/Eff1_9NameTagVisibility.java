package net.rayfall.eyesniper2.skrayfall.teams.v1_9;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
import org.eclipse.jdt.annotation.Nullable;

public class Eff1_9NameTagVisibility extends Effect {

  // (show|display) (name tags|nametags) %teamoptionstatus% for team %string%

  private Expression<OptionStatus> optionStatus;
  private Expression<String> team;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    optionStatus = (Expression<OptionStatus>) exp[0];
    team = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event evt, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (optionStatus != null && team != null) {
      Core.teamManager.setTeamOption(team.getSingle(evt).replace("\"", ""),
          Option.NAME_TAG_VISIBILITY, optionStatus.getSingle(evt));
    }
  }

}
