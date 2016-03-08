package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

//(edit|update) score [with] (group|id) %string% to %string% and %number%

public class EffEditGroupIDScore extends Effect{
	
	Expression<String> id;
	Expression<String> newName;
	Expression<Number> newScore;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		newName = (Expression<String>) e[1];
		newScore = (Expression<Number>) e[2];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt){
		if(id != null && newName != null && newScore != null){
			skRayFall.sbManager.groupUpdateScore(id.getSingle(evt).replace("\"", ""), newName.getSingle(evt).replace("\"", ""), newScore.getSingle(evt).intValue());
		}
	}

}
