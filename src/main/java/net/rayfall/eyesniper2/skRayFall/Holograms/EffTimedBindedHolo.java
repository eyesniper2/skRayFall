package net.rayfall.eyesniper2.skRayFall.Holograms;


import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

public class EffTimedBindedHolo extends Effect{
	
	private Expression<String> text;
	private Expression<Timespan> time;
	private Expression<Entity> tar;
	private Expression<Number> x;
	private Expression<Number> y;
	private Expression<Number> z;

	//bind hologram %string% to %entity% for %timespan% [offset by %number%, %number%( and|,) %number%]
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		text = (Expression<String>) e[0];
		tar = (Expression<Entity>) e[1];
		time = (Expression<Timespan>) e[2];
		x = (Expression<Number>) e[3];
		y = (Expression<Number>) e[4];
		z = (Expression<Number>) e[5];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {

		return null;
	}

	@Override
	protected void execute(final Event evt) {
		double hx = 0;
		double hy = 0;
		double hz = 0;
		if (x != null){
			hx = x.getSingle(evt).doubleValue();
		}
		if (y != null){
			hy = y.getSingle(evt).doubleValue();
		}
		if (z != null){
			hz = z.getSingle(evt).doubleValue();
		}
		final Hologram hologram = HologramsAPI.createHologram(skRayFall.plugin, tar.getSingle(evt).getLocation());
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
		final double fhx = hx;
		final double fhy = hy;
		final double fhz = hz;
		new BukkitRunnable() {
		    int ticksRun;
		    @Override
		    public void run() {
		        ticksRun++;
		        hologram.teleport(tar.getSingle(evt).getLocation().add(fhx, fhy, fhz));

		        if (ticksRun > time.getSingle(evt).getTicks()) {
		            hologram.delete();
		            cancel();
		        }
		    }
		}.runTaskTimer(skRayFall.plugin, 1L, 1L);
	}
		
	}

