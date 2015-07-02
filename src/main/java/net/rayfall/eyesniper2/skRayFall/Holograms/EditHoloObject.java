package net.rayfall.eyesniper2.skRayFall.Holograms;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EditHoloObject extends Effect{
	//edit holo object %string% to %string%
	private Expression<String> text;
	private Expression<String> id;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		text = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", "")) != null) {
			Hologram hologram = HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", ""));
			hologram.clearLines();
			String core = text.getSingle(evt).replace("\"", "");
			while (core.indexOf(";") != -1){
				String line = core.substring(0, core.indexOf(";"));
				core = core.substring(core.indexOf(";") + 1);
				if (line.startsWith("ItemStack:")){
					line = line.substring(line.indexOf(":") + 1);
					Material mat = Material.valueOf(line.toUpperCase().replace(" ", "_"));
					ItemStack stack = new ItemStack(mat, 1);
					hologram.appendItemLine(stack);
				}
				else{
					hologram.appendTextLine(line);
				}
			}
			hologram.appendTextLine(core);
			HoloManager.editHoloMap(id.getSingle(evt).replace("\"", ""), hologram);
		
	}
	
	else{
		Skript.error("That hologram does not exist!");
	}
	}

}
