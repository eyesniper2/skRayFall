package net.rayfall.eyesniper2.skRayFall.EffectLib;

import net.rayfall.eyesniper2.skRayFall.skRayFall;
import net.rayfall.eyesniper2.skRayFall.utli.YMLParticleGetter;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.effect.AtomEffect;
import de.slikey.effectlib.util.ParticleEffect;

public class EffGeneralEffectLib extends Effect{
	
	private Expression<?> target;
	private Expression<Timespan> dur;
	private Expression<String> holdEffectName;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		//Put yml stuff here
		skRayFall.plugin.reloadConfig();
		holdEffectName = (Expression<String>) e[0];
		if(!(skRayFall.plugin.getConfig().contains(holdEffectName.toString().toLowerCase().replace("\"", "")))){
			//put error message here
			Skript.error("An EffectLib Effect under that name does not exist!");
			return false;
		}
		else{
			target = e[1];
			dur = (Expression<Timespan>) e[2];
			return true;
		}
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		Bukkit.broadcastMessage("Starting");
		String effectName = holdEffectName.toString().toLowerCase().replace("\"", "");
		//For the atom
		if (skRayFall.plugin.getConfig().getString(effectName + ".type").equalsIgnoreCase("atom")){
			AtomEffect atom = new AtomEffect(skRayFall.effectManager);
			if(skRayFall.plugin.getConfig().isString(effectName + ".particle nucleus") && skRayFall.plugin.getConfig().contains(effectName + ".particle nucleus")){
				ParticleEffect nucleus = YMLParticleGetter.particleTypeGetter(skRayFall.plugin.getConfig().getString(effectName + ".particle nucleus"));
				if (nucleus == null) {
					Skript.error("There was an invalid particle type");
				}
				else{
				atom.particleNucleus = nucleus;
				}
			}
			else{
				Skript.error("There was an issue loading your particle type");
			}
			if(skRayFall.plugin.getConfig().isString(effectName + ".particle orbital") && skRayFall.plugin.getConfig().contains(effectName + ".particle orbital")){
				ParticleEffect orbital = YMLParticleGetter.particleTypeGetter(skRayFall.plugin.getConfig().getString(effectName + ".particle orbital"));
				if (orbital == null) {
					Skript.error("There was an invalid particle type");
				}
				else{
				atom.particleNucleus = orbital;
				}
			}
			else{
				Skript.error("There was an issue loading your particle type");
			}
			
			
			final Object[] os = target.getArray(evt);
			Bukkit.broadcastMessage("all checks good!");
			for (final Object o : os) {
				if (o instanceof Entity) {
					Bukkit.broadcastMessage("Started Ent");
					atom.setEntity((Entity) o);
					atom.iterations = dur.getSingle(evt).getTicks();
					atom.start();
				} 
				else if (o instanceof Location) {
					
					Bukkit.broadcastMessage("Started Loc");
					@SuppressWarnings("unchecked")
					final Expression<Location> loc = (Expression<Location>) o;
					atom.setLocation(loc.getSingle(evt));
					atom.iterations = dur.getSingle(evt).getTicks();
					atom.start();
				} 
				else {
					Bukkit.broadcastMessage("Failed");
					assert false;
				}
			}
			
		}
		else{
			Skript.error("Invalid effect type!");
		}
		
		
	}


}
