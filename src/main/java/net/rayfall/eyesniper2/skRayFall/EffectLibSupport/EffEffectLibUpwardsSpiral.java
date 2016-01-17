package net.rayfall.eyesniper2.skRayFall.EffectLibSupport;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.util.DynamicLocation;

public class EffEffectLibUpwardsSpiral extends Effect{
	
	//(spawn|create|apply) (a|the|an) upward[s] spiral (effect|formation) (at|on|for|to) %entity/location% with id %string%
	
	private Expression<?> target;
	private Expression<String> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		target = e[0];
		id = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		SpiralUpwardsEffect effect = new SpiralUpwardsEffect(skRayFall.effectManager);
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
			effect.infinite();
			effect.start();
			Boolean check = skRayFall.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}
		} else if (tar instanceof Location) {
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
			effect.infinite();
			effect.start();
			Boolean check = skRayFall.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}
		} else {
			assert false;
		}
		
		
	}

}
