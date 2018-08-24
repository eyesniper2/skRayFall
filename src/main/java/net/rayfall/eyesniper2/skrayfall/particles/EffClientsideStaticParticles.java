package net.rayfall.eyesniper2.skrayfall.particles;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.ParticleEffect.BlockData;
import de.slikey.effectlib.util.ParticleEffect.ItemData;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

@Name("Create Clientside Particles")
@Description("skRayFall's new particle system powered by EffectLib. Color's and ItemStacks can only be assigned to " +
        "certain EffectLibParticle types. Please see this " +
        "[list](https://gist.github.com/eyesniper2/07f5c0955af05c0782ad) " +
        "for the details of which effects are supported.")

public class EffClientsideStaticParticles extends Effect {

    // (create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% for %player%
    // [with data of %-itemstack%] [offset by %number%, %number%[, and] %number%] [(with|at) speed
    // %number%]

    private Expression<ParticleEffect> part;
    private Expression<Player> player;
    private Expression<Number> num;
    private Expression<Location> location;
    private Expression<ItemStack> data;
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
        player = (Expression<Player>) exp[3];
        data = (Expression<ItemStack>) exp[4];
        ox = (Expression<Number>) exp[5];
        oy = (Expression<Number>) exp[6];
        oz = (Expression<Number>) exp[7];
        speed = (Expression<Number>) exp[8];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @SuppressWarnings("deprecation")
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
                    num.getSingle(evt).intValue(), location.getSingle(evt), player.getSingle(evt));
        } else if (part.getSingle(evt).equals(ParticleEffect.ITEM_CRACK)
                && data.getSingle(evt) != null) {
            ItemData finalData =
                    new ItemData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
            part.getSingle(evt).display(finalData, xcord, ycord, zcord, finalSpeed,
                    num.getSingle(evt).intValue(), location.getSingle(evt), player.getSingle(evt));
        } else {
            part.getSingle(evt).display(xcord, ycord, zcord, finalSpeed, num.getSingle(evt).intValue(),
                    location.getSingle(evt), player.getSingle(evt));
        }
    }
}
