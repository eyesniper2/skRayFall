package net.rayfall.eyesniper2.skRayFall.Teams.V1_9;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class Eff1_9NameTagVisibility extends Effect{
	
	//(show|display) (name tags|nametags) %teamoptionstatus% for team %string%
	
	private Expression<OptionStatus> optionStatus;
	private Expression<String> team;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		optionStatus = (Expression<OptionStatus>) e[0];
		team = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(optionStatus != null && team != null){
			skRayFall.teamManager.setTeamOption(team.getSingle(evt).replace("\"", ""), Option.NAME_TAG_VISIBILITY, optionStatus.getSingle(evt));
		}
	}

}
