package net.rayfall.eyesniper2.skRayFall.EffectLib;

import javax.annotation.Nullable;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.effect.BleedEffect;

public class EffEffectLibBleed extends Effect{
	
	private Expression<?> target;
	
	private Expression<Timespan> dur;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		target = e[0];
		dur = (Expression<Timespan>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		final Object[] os = target.getArray(evt);
		for (final Object o : os) {
			BleedEffect Bleed = new BleedEffect(skRayFall.effectManager);
			if (o instanceof Entity) {
				Bleed.setEntity((Entity) o);
				Bleed.iterations = dur.getSingle(evt).getTicks();
				Bleed.start();
			} 
			else if (o instanceof Location) {
				@SuppressWarnings("unchecked")
				final Expression<Location> loc = (Expression<Location>) o;
				Bleed.setLocation(loc.getSingle(evt));
				if (dur != null){
					Bleed.iterations = dur.getSingle(evt).getTicks();
				}
				Bleed.start();
			} 
			else {
				assert false;
			}
		}
		
		
	}
	

}
