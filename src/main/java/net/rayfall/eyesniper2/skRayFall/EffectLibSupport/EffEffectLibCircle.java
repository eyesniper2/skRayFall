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
import de.slikey.effectlib.effect.CircleEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibCircle extends Effect{
	
	//(spawn|create|apply) (a|the|an) circle (effect|formation) (at|on|for) %entity/location% with id %string% [with particle[s] %-effectlibparticle%] [(and|with) radius %-number%]
	
	private Expression<?> target;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;
	private Expression<Number> rad;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		target = e[0];
		id = (Expression<String>) e[1];
		particle = (Expression<ParticleEffect>) e[3];
		rad = (Expression<Number>) e[4];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		CircleEffect effect = new CircleEffect(skRayFall.effectManager);
		if (tar instanceof Entity) {
			effect.setEntity((Entity) tar);
		} else if (tar instanceof Location) {
			effect.setLocation((Location) tar);
		} else {
			assert false;
		}
		if(particle != null){
			effect.particle = particle.getSingle(evt);
		}
		if(rad != null){
			effect.radius = rad.getSingle(evt).floatValue();
		}
		effect.infinite();
		effect.start();
		Boolean check = skRayFall.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}
		
	}

}
