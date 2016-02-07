package net.rayfall.eyesniper2.skRayFall.Capes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import tfw.Capes.General.CapeAPI;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffWearCape extends Effect{
	//make %player% wear cape %itemstack%
	
	private Expression<ItemStack> item;
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		item = (Expression<ItemStack>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(player != null && item != null && item.getSingle(evt).getType().equals(Material.BANNER)){
			CapeAPI.equipCapeFromItemstack(player.getSingle(evt), item.getSingle(evt));
		}
	}

}
