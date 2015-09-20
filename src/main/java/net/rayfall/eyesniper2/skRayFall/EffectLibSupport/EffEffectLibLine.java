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
import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibLine extends Effect{
	
	//(spawn|create|apply) (a|the|an) line (effect|formation) from %entity/location% to %entity/location% with id %string% [with particle[s] %-effectlibparticle%]
	
	private Expression<?> start;
	private Expression<?> end;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		start = e[0];
		end = e[1];
		id = (Expression<String>) e[2];
		particle = (Expression<ParticleEffect>) e[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object s = start.getSingle(evt);
		Object en = end.getSingle(evt);
		LineEffect effect = new LineEffect(skRayFall.effectManager);
		if (s instanceof Entity) {
			effect.setEntity((Entity) s);
		} else if (s instanceof Location) {
			effect.setLocation((Location) s);
		} else {
			assert false;
		}
		if (en instanceof Entity) {
			effect.setTargetEntity((Entity) en);
		} else if (en instanceof Location) {
			effect.setTarget((Location) en);
		} else {
			assert false;
		}
		if(particle != null){
			effect.particle = particle.getSingle(evt);
		}
		effect.infinite();
		effect.start();
		Boolean check = skRayFall.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
		if (!check) {
			effect.cancel();
		}
	}

}
