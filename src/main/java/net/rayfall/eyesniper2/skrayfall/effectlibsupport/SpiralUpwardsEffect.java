package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;

import org.bukkit.Color;
import org.bukkit.Location;


/*
 * Effects contents provided by SteeZyyy from video
 * https://www.youtube.com/watch?v=XMeck_nTdVA&ab_channel=SteeZyyy This effect was then adapted to
 * work within effectLib. The addition of the effect was requested by Orangejuiceyummy
 */

public class SpiralUpwardsEffect extends Effect {

    public ParticleEffect particle = ParticleEffect.FLAME;
    public Color particleColor = null;

    public double heightScale = 0;
    public double radius = 2;

    protected double oldT = 0;

    /**
     * Generates the Spiral Upwards Effect.
     *
     * <p>Effects contents provided by SteeZyyy from video
     * https://www.youtube.com/watch?v=XMeck_nTdVA&ab_channel=SteeZyyy This effect was then adapted to
     * work within effectLib.
     *
     * <p>The addition of the effect was requested by Orangejuiceyummy.
     *
     * @param effectManager The effectManager to use this effect
     */
    public SpiralUpwardsEffect(EffectManager effectManager) {
        super(effectManager);
        type = EffectType.REPEATING;
        period = 1;
        iterations = 600;
    }

    @Override
    public void onRun() {
        Location location = getLocation();
        if (oldT == 0) {
            oldT = heightScale + Math.PI / 16;
        } else {
            oldT = oldT + Math.PI / 16;
        }
        double xposition = radius * Math.cos(oldT);
        double yposition = radius * Math.sin(oldT);
        double zposition = radius * Math.sin(oldT);
        location.add(xposition, yposition, zposition);
        display(particle, location, particleColor);
        location.subtract(xposition, yposition, zposition);
    }
}
