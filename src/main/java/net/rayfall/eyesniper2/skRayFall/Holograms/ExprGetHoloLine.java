package net.rayfall.eyesniper2.skRayFall.Holograms;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprGetHoloLine extends SimpleExpression<String>{
	
	//text in line %number% of holo[gram| object] %string%
	
	private Expression<String> id;
	private Expression<Number> line;
	

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[1];
		line = (Expression<Number>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected String[] get(Event evt) {
		if (HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", "")) != null) {
			Hologram hologram = HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", ""));
			String finalLine = hologram.getLine(line.getSingle(evt).intValue()).toString();
			if(finalLine.indexOf("text=") != -1){
				finalLine = finalLine.substring(finalLine.indexOf("text=") + 5, finalLine.indexOf("]"));
				return new String[] {finalLine};
			}
			else if(finalLine.indexOf("itemStack=") != -1){
				finalLine = finalLine.substring(finalLine.indexOf("itemStack=") + 10, finalLine.indexOf(","));
				return new String[] {finalLine};
			}
			else
				return new String[] {null};
			
		}
		else
			return new String[] {null};
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

}
