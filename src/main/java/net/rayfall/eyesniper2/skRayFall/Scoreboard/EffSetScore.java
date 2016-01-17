package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetScore extends Effect{
	
	private Expression<String> name;
	private Expression<Player> player;
	private Expression<Number> num;
	//set score %string% in sidebar of %player% to %number%

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		name = (Expression<String>) e[0];
		player = (Expression<Player>) e[1];
		num = (Expression<Number>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (player == null || num == null ||  player.getSingle(evt).getScoreboard() == null){
			Skript.error("This player is either not online or has yet to have a scoreboard set for them");
		}
		else{
		Scoreboard sb = player.getSingle(evt).getScoreboard();
		Objective objective = sb.getObjective(DisplaySlot.SIDEBAR);
		Score score = objective.getScore(name.getSingle(evt).replace("\"", ""));
		score.setScore(num.getSingle(evt).intValue());
	}
	}

}
