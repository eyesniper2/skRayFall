package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffEditIDScore extends Effect{
	
	//(edit|update) score [with] id %string% to %string% and %number%

	private Expression<String> id;
	private Expression<Number> slot;
	private Expression<String> newName; 
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id =  (Expression<String>) e[0];
		newName = (Expression<String>) e[1];
		slot =  (Expression<Number>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		String i = id.getSingle(evt).replace("\"", "");
		if(skRayFall.sbManager.getScore(i) != null){
			skRayFall.sbManager.updateSingleScore(i, newName.getSingle(evt).replace("\"", ""), slot.getSingle(evt).intValue());
		}
		else{
			Skript.error("The score id "+ i +" does not exist!");
		}
	}

}
