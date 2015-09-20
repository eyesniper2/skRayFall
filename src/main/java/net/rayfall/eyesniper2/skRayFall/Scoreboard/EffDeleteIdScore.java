package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffDeleteIdScore extends Effect{
	
	//(delete|remove) score [with] id %string%
	
	private Expression<String> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		String i = id.getSingle(evt).replace("\"", "");
		if(skRayFall.sbManager.getScore(i) != null){
			Score s = skRayFall.sbManager.getScore(i);
			Objective obj = s.getObjective();
			obj.getScoreboard().resetScores(s.getEntry());
			skRayFall.sbManager.deleteScoreID(i);
		}
		
		
	}

}
