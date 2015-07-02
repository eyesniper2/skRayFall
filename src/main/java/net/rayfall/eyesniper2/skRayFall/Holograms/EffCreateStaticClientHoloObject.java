package net.rayfall.eyesniper2.skRayFall.Holograms;


import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateStaticClientHoloObject extends Effect{

	//create client side holo object %string% with id %string% at %location% to %player%
	
		private Expression<String> text;
		private Expression<String> id;
		private Expression<Location> loc;
		private Expression<Player> player;
		@SuppressWarnings("unchecked")
		@Override
		public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
				ParseResult arg3) {
			id = (Expression<String>) e[1];
			text = (Expression<String>) e[0];
			loc = (Expression<Location>) e[2];
			player = (Expression<Player>) e[3];
			return true;
		}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {

		return null;
	}

	@Override
	protected void execute(Event evt) {
		final Hologram hologram = HologramsAPI.createHologram(skRayFall.plugin, loc.getSingle(evt));
		VisibilityManager vM = hologram.getVisibilityManager();
		vM.showTo(player.getSingle(evt));
		vM.setVisibleByDefault(false);
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
		if (HoloManager.addToHoloMap(id.getSingle(evt).replace("\"", ""), hologram) == false){
			hologram.delete();
		}
		
	}

}
