package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.effect.ImageEffect;
import de.slikey.effectlib.util.DynamicLocation;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class EffEffectImage extends Effect {

  // (spawn|create|apply) (a|the|an) [particle] (image|gif) (effect|formation) of %string%
  // (at|on|for|to) %entity/location% with id %string%

  Expression<String> image;
  Expression<?> target;
  Expression<String> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    image = (Expression<String>) exp[0];
    target = exp[1];
    id = (Expression<String>) exp[2];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    Object tar = target.getSingle(evt);
    ImageEffect effect = null;
    try {
      effect = new ImageEffect(Core.effectManager);
      File image = new File(id.getSingle(evt).replace("\"", ""));
      effect.loadFile(image);
    } catch (IOException exception) {
      exception.printStackTrace();
      assert false;
    }
    if (tar instanceof Entity) {
      effect.setDynamicOrigin(new DynamicLocation((Entity) tar));
    } else if (tar instanceof Location) {
      effect.setDynamicOrigin(new DynamicLocation((Location) tar));
    } else {
      assert false;
    }
    Boolean check = Core.effLibManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
    if (!check) {
      effect.cancel();
    }

  }

}
