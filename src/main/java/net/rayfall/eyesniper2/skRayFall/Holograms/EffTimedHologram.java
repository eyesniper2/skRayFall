package net.rayfall.eyesniper2.skRayFall.Holograms;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

public class EffTimedHologram extends Effect{
	
	//create hologram %string% at %location% for %timespan%
	
	private Expression<String> text;
	private Expression<Timespan> time;
	private Expression<Location> loc;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		text = (Expression<String>) e[0];
		loc = (Expression<Location>) e[1];
		time = (Expression<Timespan>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		final Hologram hologram = HologramsAPI.createHologram(skRayFall.plugin, loc.getSingle(evt));
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
		Bukkit.getScheduler().runTaskLater(skRayFall.plugin, new Runnable() {
		    @Override
		    public void run() {
		       hologram.delete();
		    }
		}, time.getSingle(evt).getTicks());
	}

}
