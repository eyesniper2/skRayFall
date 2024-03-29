package net.rayfall.eyesniper2.skrayfall.v1_8;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.minecraft.server.v1_8_R1.ChatSerializer;
import net.minecraft.server.v1_8_R1.IChatBaseComponent;
import net.minecraft.server.v1_8_R1.PacketPlayOutChat;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Action Bar")
@Description({"Display Action Bar by:",
        "* Player",
        "* Text",
        "This will display text above the player's hotbar for a few seconds. " +
        "This is like the text you receive when changing the item in your hand"})
@DocumentationId("EffActionBar")
public class EffActionBarV1_8 extends Effect {

    // set action bar of %player% to %string%

    private Expression<Player> player;
    private Expression<String> text;

    private Class<?> CraftPlayer, PacketPlayOutChat, IChatBaseComponent;

    final private String version = Bukkit.getBukkitVersion();

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[0];
        text = (Expression<String>) exp[1];

        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        for (Player p : player.getAll(evt)) {
            IChatBaseComponent payload =
                    ChatSerializer.a("{\"text\": \"" + text.getSingle(evt).replace("\"", "") + "\"}");
            PacketPlayOutChat packet = new PacketPlayOutChat(payload, (byte) 2);
            ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
        }
    }
}
