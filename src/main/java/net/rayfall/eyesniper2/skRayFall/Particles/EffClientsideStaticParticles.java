package net.rayfall.eyesniper2.skRayFall.Particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.util.ParticleEffect;
import de.slikey.effectlib.util.ParticleEffect.BlockData;
import de.slikey.effectlib.util.ParticleEffect.ItemData;

public class EffClientsideStaticParticles extends Effect{
	
	//(create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% for %player% [with data of %-itemstack%] [offset by %number%, %number%[, and] %number%] [(with|at) speed %number%]

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
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		num = (Expression<Number>) e[0];
		part = (Expression<ParticleEffect>) e[1];
		location = (Expression<Location>) e[2];
		player = (Expression<Player>) e[3];
		data = (Expression<ItemStack>) e[4];
		ox = (Expression<Number>) e[5];
		oy = (Expression<Number>) e[6];
		oz = (Expression<Number>) e[7];
		speed = (Expression<Number>) e[8];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@SuppressWarnings("deprecation")
	protected void execute(Event evt) {
		float x = 0;
		float y = 0;
		float z = 0;
		float finalSpeed = 1;
		if(ox.getSingle(evt) != null){
			x = ox.getSingle(evt).floatValue();
		}
		if(oy.getSingle(evt) != null){
			y = oy.getSingle(evt).floatValue();
		}
		if(oz.getSingle(evt) != null){
			z = oz.getSingle(evt).floatValue();
		}
		if(speed.getSingle(evt) != null){
			finalSpeed = speed.getSingle(evt).floatValue();
		}
		if((part.getSingle(evt).equals(ParticleEffect.BLOCK_CRACK)||part.getSingle(evt).equals(ParticleEffect.BLOCK_DUST)) && data.getSingle(evt) != null){
			BlockData fData = new BlockData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
			part.getSingle(evt).display(fData, x, y, z, finalSpeed,num.getSingle(evt).intValue(), location.getSingle(evt), player.getSingle(evt));
		}
		else if(part.getSingle(evt).equals(ParticleEffect.ITEM_CRACK) && data.getSingle(evt) != null){
			ItemData fData = new ItemData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
			part.getSingle(evt).display(fData, x, y, z, finalSpeed,num.getSingle(evt).intValue(), location.getSingle(evt), player.getSingle(evt));
		}
		else{
			part.getSingle(evt).display(x, y, z, finalSpeed, num.getSingle(evt).intValue(), location.getSingle(evt), player.getSingle(evt));
		}
		
	}

}
