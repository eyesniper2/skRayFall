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

public class Eff1_9MessageOnDeathRule extends Effect{
	
	//(show|display) death message[s] %teamoptionstatus% for team %string%
	
	private Expression<OptionStatus> status;
	private Expression<String> team;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		status = (Expression<OptionStatus>) e[0];
		team = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(team != null){
			skRayFall.teamManager.setTeamOption(team.getSingle(evt).replace("\"", "")
					, Option.DEATH_MESSAGE_VISIBILITY, status.getSingle(evt));
		}		
	}

}
