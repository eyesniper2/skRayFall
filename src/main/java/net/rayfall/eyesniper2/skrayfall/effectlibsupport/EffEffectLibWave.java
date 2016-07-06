package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.effect.WaveEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffEffectLibWave extends Effect {

  // (spawn|create|apply) (a|the|an) wave (effect|formation) at %entity/location% with id %string%
  // [with particle[s] %-effectlibparticle%]

  private Expression<?> target;
  private Expression<String> id;
  private Expression<ParticleEffect> particle;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    target = exp[0];
    id = (Expression<String>) exp[1];
    particle = (Expression<ParticleEffect>) exp[2];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    Object tar = target.getSingle(evt);
    WaveEffect effect = new WaveEffect(Core.effectManager);
    if (tar instanceof Entity) {
      effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
      if (particle != null) {
        effect.particle = particle.getSingle(evt);
      }
      effect.infinite();
      effect.start();
      Boolean check = Core.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
      if (!check) {
        effect.cancel();
      }
    } else if (tar instanceof Location) {
      effect.setDynamicOrigin(new DynamicLocation((Location) tar));
      effect.infinite();
      if (particle != null) {
        effect.particle = particle.getSingle(evt);
      }
      effect.start();
      Boolean check = Core.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
      if (!check) {
        effect.cancel();
      }
    } else {
      assert false;
    }

  }

}
