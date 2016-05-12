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

public class Eff1_9TeamCollisionRule extends Effect{
	
	//(set|define) team collision [rule] as %teamoptionstatus% for team %string%
	
	private Expression<OptionStatus> optionStatus;
	private Expression<String> team;
	

	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(team != null && optionStatus != null){
			skRayFall.teamManager.setTeamOption(team.getSingle(evt).replace("\"", ""), Option.DEATH_MESSAGE_VISIBILITY, optionStatus.getSingle(evt));
		}
	}

}
