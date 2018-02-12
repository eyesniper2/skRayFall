package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.effect.TextEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffEffectLibText extends Effect {
  // create a text (effect|formation) with text %string% as %effectlibparticle% (at|on|for) %% with
  // id %string% [and %number% large]

  private Expression<?> target;
  private Expression<String> dtext;
  private Expression<ParticleEffect> part;
  private Expression<String> id;
  private Expression<Number> size;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    dtext = (Expression<String>) exp[0];
    part = (Expression<ParticleEffect>) exp[1];
    target = exp[2];
    id = (Expression<String>) exp[3];
    size = (Expression<Number>) exp[4];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    Object tar = target.getSingle(evt);
    TextEffect text = new TextEffect(Core.effectManager);
    if (tar instanceof Entity) {
      text.setDynamicOrigin(new DynamicLocation((Entity) tar));
      text.particle = part.getSingle(evt);
      text.text = dtext.getSingle(evt).replace("\"", "");
      if (size.getSingle(evt) != null) {
        text.size = size.getSingle(evt).floatValue();
      }
      text.infinite();
      text.start();
      Boolean check = Core.rayfallEffectManager.setEffect(text, id.getSingle(evt).replace("\"", ""));
      if (!check) {
        text.cancel();
      }
    } else if (tar instanceof Location) {
      text.setDynamicOrigin(new DynamicLocation((Location) tar));
      text.particle = part.getSingle(evt);
      text.text = dtext.getSingle(evt).replace("\"", "");
      if (size.getSingle(evt) != null) {
        text.size = size.getSingle(evt).floatValue();
      }
      text.infinite();
      text.start();
      Boolean check = Core.rayfallEffectManager.setEffect(text, id.getSingle(evt).replace("\"", ""));
      if (!check) {
        text.cancel();
      }
    } else {
      assert false;
    }
  }

}


