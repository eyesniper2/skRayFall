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
import de.slikey.effectlib.effect.AnimatedBallEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

public class EffEffectLibAnimatedBallEffect extends Effect{
	
	//(spawn|create|apply) (a|the|an) animated ball (effect|formation) (at|on|for|to) %entity/location% with id %string% [with particle %-effectlibparticle%][ off set by %number%, %number%(,| and) %number%]
	
	private Expression<?> target;
	private Expression<String> id;
	private Expression<ParticleEffect> particle;
	private Expression<Number> xOffSet;
	private Expression<Number> yOffSet;
	private Expression<Number> zOffSet;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		target = e[0];
		id = (Expression<String>) e[1];
		particle = (Expression<ParticleEffect>) e[2];
		xOffSet = (Expression<Number>) e[3];
		yOffSet = (Expression<Number>) e[4];
		zOffSet = (Expression<Number>) e[5];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Object tar = target.getSingle(evt);
		AnimatedBallEffect effect = new AnimatedBallEffect(skRayFall.effectManager);
		if (tar instanceof Entity) {
			effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
		}
		else if (tar instanceof Location){
			effect.setDynamicOrigin(new DynamicLocation((Location) tar));
		}
		else {
			assert false;
		}
			if(particle != null){
				effect.particle = particle.getSingle(evt);
			}
			if(xOffSet != null && yOffSet != null && zOffSet != null){
				effect.xOffset = xOffSet.getSingle(evt).floatValue();
				effect.yOffset = yOffSet.getSingle(evt).floatValue();
				effect.zOffset = zOffSet.getSingle(evt).floatValue();
			}
			effect.infinite();
			effect.start();
			Boolean check = skRayFall.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
			if (!check) {
				effect.cancel();
			}

		 
		
	}

}
