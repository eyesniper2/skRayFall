package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetPlayerListName extends Effect{
	private Expression<Player> player;
	private Expression<String> name;
	
	//set %players% tab name to %string%

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player =  (Expression<Player>) e[0];
		name = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (player.getSingle(evt) instanceof Player && player.getSingle(evt).isOnline()){
		player.getSingle(evt).setPlayerListName(name.getSingle(evt).replace("\"", ""));
		}
		else{
			return;
		}
	}

}
