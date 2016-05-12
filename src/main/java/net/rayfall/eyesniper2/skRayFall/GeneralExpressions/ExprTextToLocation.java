package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTextToLocation extends SimpleExpression<Location>{
	
	//%string% converted to location
	
	private Expression<String> text;

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		text = (Expression<String>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Location[] get(Event evt) {
		if(text != null){
			String[] parts = text.getSingle(evt).replace("\"", "").split(":");
			World w = Bukkit.getWorld(parts[0]);
			if(w == null){
				return null;
			}
			String[] cords = parts[1].split(",");
			if(cords.length > 3){
				return null;
			}
			Location loc;
			try{
				loc = new Location(w, Double.parseDouble(cords[0]), Double.parseDouble(cords[1]), Double.parseDouble(cords[2]));
			}
			catch(NumberFormatException e){
				return null;
			}
			catch(NullPointerException e){
				return null;
			}
			return new Location[] {loc};
		}
		return null;
	}

}
