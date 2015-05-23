package net.rayfall.eyesniper2.skRayFall.V1_8_4;

import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffParticlesV1_8_4 extends Effect{	
	//show %number% of %string% particle[s] at %location% for %player% [offset with %number%, %number% (and|,) %number%]
	private Expression<Number> partNum;
	private Expression<Player> player;
	private Expression<Location> location;
	private Expression<String> type;
	private Expression<Number> x;
	private Expression<Number> y;
	private Expression<Number> z;
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		partNum = (Expression<Number>) e[0];
		type = (Expression<String>) e[1];
		location = (Expression<Location>) e[2];
		x = (Expression<Number>) e[4];
		y = (Expression<Number>) e[5];
		z = (Expression<Number>) e[6];
		player = (Expression<Player>) e[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		float hx = 0f;
		float hy = 0f;
		float hz = 0f;
		float id = 0;
		int[] array = new int[0];
		String core = type.getSingle(evt);
		if (x != null){
			hx = x.getSingle(evt).floatValue();
		}
		if (y != null){
			hy = y.getSingle(evt).floatValue();
		}
		if (z != null){
			hz = z.getSingle(evt).floatValue();
		}
		if (core.toUpperCase().replace(" ", "_").contains("BLOCK_CRACK")||core.toUpperCase().replace(" ", "_").contains("BLOCK_DUST")){
			int index = type.getSingle(evt).lastIndexOf("_");
			try{
			id = Integer.parseInt(type.getSingle(evt).substring(index + 1));
			}
			catch(Exception e){
				Skript.error("Could not parse datavalue!");
				id = 0;
			}
			core = core.substring(0, index);
			array = new int[1];
			Bukkit.broadcastMessage("id: "+ id + "core: " + core);
		}
		EnumParticle part = PacketParticleGetterV1_8_4.get(core);

		PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(part, true, (float) location.getSingle(evt).getX(), (float) location.getSingle(evt).getY(), (float) location.getSingle(evt).getZ(), hx, hy, hz, id, partNum.getSingle(evt).intValue(), array);
		((CraftPlayer) player.getSingle(evt)).getHandle().playerConnection.sendPacket(packet);
	}

}
