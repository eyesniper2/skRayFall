package net.rayfall.eyesniper2.skRayFall.V1_8;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffActionBarV1_8 extends Effect{
	
	//set action bar of %player% to %string% 
	
	private Expression<Player> player;
	private Expression<String> text;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		text = (Expression<String>) e[1];
		
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
        IChatBaseComponent c = ChatSerializer.a("{\"text\": \"" + text.getSingle(evt).replace("\"", "") + "\"}");
        PacketPlayOutChat packet = new PacketPlayOutChat(c,(byte) 2);
        ((CraftPlayer) player.getSingle(evt)).getHandle().playerConnection.sendPacket(packet);
	}

}
