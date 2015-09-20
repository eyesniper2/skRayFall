package net.rayfall.eyesniper2.skRayFall.Particles;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class EffStaticParticles extends Effect{
	
	//(create|display|show) %number% [of] %effectlibparticle% particle[s] at %location% [(with data of %-itemstack%|with color %number%, %number%[, and] %number%)] [offset by %number%, %number%[, and] %number%] [(with|at) speed %number%]
	
	private Expression<ParticleEffect> part;
	private Expression<Number> num;
	private Expression<Location> location;
	private Expression<ItemStack> data;
	private Expression<Number> r;
	private Expression<Number> g;
	private Expression<Number> b;
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
		data = (Expression<ItemStack>) e[3];
		r = (Expression<Number>) e[4];
		g = (Expression<Number>) e[5];
		b = (Expression<Number>) e[6];
		ox = (Expression<Number>) e[7];
		oy = (Expression<Number>) e[8];
		oz = (Expression<Number>) e[9];
		speed = (Expression<Number>) e[10];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
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
			part.getSingle(evt).display(fData, x, y, z, finalSpeed,num.getSingle(evt).intValue(), location.getSingle(evt), (double)50);
		}
		else if(part.getSingle(evt).equals(ParticleEffect.ITEM_CRACK) && data.getSingle(evt) != null){
			ItemData fData = new ItemData(data.getSingle(evt).getType(), data.getSingle(evt).getData().getData());
			part.getSingle(evt).display(fData, x, y, z, finalSpeed,num.getSingle(evt).intValue(), location.getSingle(evt), (double)50);
		}
		else if((part.getSingle(evt).equals(ParticleEffect.REDSTONE)||part.getSingle(evt).equals(ParticleEffect.SPELL_MOB_AMBIENT) ||part.getSingle(evt).equals(ParticleEffect.SPELL_MOB))&& r.getSingle(evt) != null && g.getSingle(evt) != null && b.getSingle(evt) != null){
			BlockData fdata = new BlockData(Material.GRASS, (byte) 0);
			Color c = Color.fromRGB(r.getSingle(evt).intValue(), g.getSingle(evt).intValue(), b.getSingle(evt).intValue());
			for(int i = 0; i <= num.getSingle(evt).intValue(); i++){
				part.getSingle(evt).display(fdata, location.getSingle(evt), c, (double) 50,x, y, z, finalSpeed, num.getSingle(evt).intValue());
			}
		}
		else{
			part.getSingle(evt).display(x, y, z, finalSpeed, num.getSingle(evt).intValue(), location.getSingle(evt), (double) 50);
		}
		
		
	}
	
	
	}


