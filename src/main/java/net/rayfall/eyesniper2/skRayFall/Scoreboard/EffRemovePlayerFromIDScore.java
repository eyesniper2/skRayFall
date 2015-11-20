package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRemovePlayerFromIDScore extends Effect{
	
	//(delete|remove) %player% from group [id based] score %string%
	
	Expression<Player> player;
	Expression<String> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		id = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		skRayFall.sbManager.removePlayerFromGroupScore(id.getSingle(evt).replace("\"", ""), player.getSingle(evt));
	}
	

}
