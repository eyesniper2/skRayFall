package net.rayfall.eyesniper2.skRayFall.EffectLibSupport;

import org.bukkit.Color;
import org.bukkit.Location;
import de.slikey.effectlib.Effect;
import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.util.ParticleEffect;


/* Effects contents provided by SteeZyyy from video https://www.youtube.com/watch?v=XMeck_nTdVA&ab_channel=SteeZyyy 
   This effect was then adapted to work within effectLib.
   The addition of the effect was requested by Orangejuiceyummy
 */

public class SpiralUpwardsEffect extends Effect{
	
	public ParticleEffect particle = ParticleEffect.FLAME;
    public Color particleColor = null;
    
    public double heightScale = 0;
    public double raidus = 2;
    
    protected double oldT = 0;

	public SpiralUpwardsEffect(EffectManager effectManager) {
		super(effectManager);
		type = EffectType.REPEATING;
	    period = 1;
	    iterations = 600;
	}

	@Override
	public void onRun() {
		Location location = getLocation();
		if(oldT == 0){
			oldT = heightScale + Math.PI/16;
		}
		else{
			oldT = oldT + Math.PI/16;
		}
        double x = raidus*Math.cos(oldT);
        double y = raidus*Math.sin(oldT);
        double z = raidus*Math.sin(oldT);
        location.add(x,y,z);
        display(particle, location, particleColor);
        location.subtract(x,y,z);
	}

}
