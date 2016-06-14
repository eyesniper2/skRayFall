package net.rayfall.eyesniper2.skRayFall.Holograms;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondHologramExists extends Condition{
	
	private Expression<String> id;
	
	//(holo object|hologram) %string% exists

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event evt) {
		if (id != null){
			return HoloManager.isInHoloMap(id.getSingle(evt).replace("\"", ""));
		}
		return false;
	}

}
