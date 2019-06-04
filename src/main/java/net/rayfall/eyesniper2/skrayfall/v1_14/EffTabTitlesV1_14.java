package net.rayfall.eyesniper2.skrayfall.v1_14;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.lang.reflect.Field;

@Name("Set Tab Header & Footer")
@Description({"Modify Tab by:",
        "* Header",
        "* Footer",
        "* Player",
        "This will display a client side header and footer for a player"})
@DocumentationId("EffTabTitles")
public class EffTabTitlesV1_14 extends Effect {

    // set tab header to %string% and footer to %string% for %player%

    private Expression<Player> player;
    private Expression<String> coreHeader;
    private Expression<String> coreFooter;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[2];
        coreHeader = (Expression<String>) exp[0];
        coreFooter = (Expression<String>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        IChatBaseComponent header =
                IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + coreHeader.getSingle(evt).replace("\"", "") + "\"}");
        IChatBaseComponent footer =
                IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + coreFooter.getSingle(evt).replace("\"", "") + "\"}");
        PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field headerField = packet.getClass().getDeclaredField("header");
            headerField.setAccessible(true);
            headerField.set(packet, header);
            headerField.setAccessible(!headerField.isAccessible());

            Field footerField = packet.getClass().getDeclaredField("footer");
            footerField.setAccessible(true);
            footerField.set(packet, footer);
            footerField.setAccessible(!footerField.isAccessible());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ((CraftPlayer) player.getSingle(evt)).getHandle().playerConnection.sendPacket(packet);
    }
}