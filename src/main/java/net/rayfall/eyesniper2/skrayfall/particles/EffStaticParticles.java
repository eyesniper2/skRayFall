package net.rayfall.eyesniper2.skrayfall.particles;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.ParticleEffect.BlockData;
import de.slikey.effectlib.util.ParticleEffect.ItemData;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import java.util.Random;

public class EffStaticParticles extends Effect {

  // (create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% [(with data
  // of %-itemstack%|with color %number%, %number%[, and] %number%)] [offset by %number%, %number%[,
  // and] %number%] [(with|at) speed %number%]

  private Expression<ParticleEffect> part;
  private Expression<Number> num;
  private Expression<Location> location;
  private Expression<ItemStack> data;
  private Expression<Number> red;
  private Expression<Number> green;
  private Expression<Number> blue;
  private Expression<Number> ox;
  private Expression<Number> oy;
  private Expression<Number> oz;
  private Expression<Number> speed;


  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    num = (Expression<Number>) exp[0];
    part = (Expression<ParticleEffect>) exp[1];
    location = (Expression<Location>) exp[2];
    data = (Expression<ItemStack>) exp[3];
    red = (Expression<Number>) exp[4];
    green = (Expression<Number>) exp[5];
    blue = (Expression<Number>) exp[6];
    ox = (Expression<Number>) exp[7];
    oy = (Expression<Number>) exp[8];
    oz = (Expression<Number>) exp[9];
    speed = (Expression<Number>) exp[10];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @SuppressWarnings("deprecation")
  @Override
  protected void execute(Event evt) {
    float xcord = 0;
    float ycord = 0;
    float zcord = 0;
    float finalSpeed = 1;
    if (ox.getSingle(evt) != null) {
      xcord = ox.getSingle(evt).floatValue();
    }
    if (oy.getSingle(evt) != null) {
      ycord = oy.getSingle(evt).floatValue();
    }
    if (oz.getSingle(evt) != null) {
      zcord = oz.getSingle(evt).floatValue();
    }
    if (speed.getSingle(evt) != null) {
      finalSpeed = speed.getSingle(evt).floatValue();
    }
    if ((part.getSingle(evt).equals(ParticleEffect.BLOCK_CRACK)
        || part.getSingle(evt).equals(ParticleEffect.BLOCK_DUST)) && data.getSingle(evt) != null) {
      BlockData finalData =
          new BlockData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
      part.getSingle(evt).display(finalData, xcord, ycord, zcord, finalSpeed,
          num.getSingle(evt).intValue(), location.getSingle(evt), 50);
    } else if (part.getSingle(evt).equals(ParticleEffect.ITEM_CRACK)
        && data.getSingle(evt) != null) {
      ItemData finalData =
          new ItemData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
      part.getSingle(evt).display(finalData, xcord, ycord, zcord, finalSpeed,
          num.getSingle(evt).intValue(), location.getSingle(evt), 50);
    } else if ((part.getSingle(evt).equals(ParticleEffect.REDSTONE)
        || part.getSingle(evt).equals(ParticleEffect.SPELL_MOB_AMBIENT)
        || part.getSingle(evt).equals(ParticleEffect.SPELL_MOB)) && red.getSingle(evt) != null
        && green.getSingle(evt) != null && blue.getSingle(evt) != null) {
      BlockData finalData = new BlockData(Material.GRASS, (byte) 0);
      Color color = Color.fromRGB(red.getSingle(evt).intValue(), green.getSingle(evt).intValue(),
          blue.getSingle(evt).intValue());
      if (ox.getSingle(evt) != null && oy.getSingle(evt) != null && oz.getSingle(evt) != null) {
        Random rand = new Random();
        double xmin = -(xcord / 2);
        double xmax = (xcord / 2);
        double ymin = -(ycord / 2);
        double ymax = (ycord / 2);
        double zmin = -(zcord / 2);
        double zmax = (zcord / 2);
        for (int i = 0; i <= num.getSingle(evt).intValue(); i++) {
          part.getSingle(evt).display(finalData,
              location.getSingle(evt).add(xmin + (xmax - xmin) * rand.nextDouble(),
                  ymin + (ymax - ymin) * rand.nextDouble(),
                  zmin + (zmax - zmin) * rand.nextDouble()),
              color, 50, xcord, ycord, zcord, finalSpeed, num.getSingle(evt).intValue());
        }
      } else {
        for (int i = 0; i <= num.getSingle(evt).intValue(); i++) {
          part.getSingle(evt).display(finalData, location.getSingle(evt), color, 50, xcord, ycord,
              zcord, finalSpeed, num.getSingle(evt).intValue());
        }
      }
    } else {
      part.getSingle(evt).display(xcord, ycord, zcord, finalSpeed, num.getSingle(evt).intValue(),
          location.getSingle(evt), 50);
    }


  }


}


