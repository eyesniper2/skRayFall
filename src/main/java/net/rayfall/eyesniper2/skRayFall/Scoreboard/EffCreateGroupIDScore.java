package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateGroupIDScore extends Effect{
	private Expression<String> score;
	private Expression<String> id;
	private Expression<Number> value;
	private Expression<Player> players;
	
	
	// (set|create) group id [based] score %string% in sidebar for %players% to %number% with id %string%
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		score = (Expression<String>) e[0];
		players = (Expression<Player>) e[1];
		value = (Expression<Number>) e[2];
		id = (Expression<String>) e[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		String finalID = id.getSingle(evt).replace("\"", "");
		skRayFall.sbManager.adjustGroupIDScore(finalID, score.getSingle(evt).replace("\"", ""), value.getSingle(evt).intValue());
		for(Player p : players.getArray(evt)){
			if(p != null && finalID != null){
				skRayFall.sbManager.addPlayerToGroupID(finalID, p);
			}
		}
	}

}
