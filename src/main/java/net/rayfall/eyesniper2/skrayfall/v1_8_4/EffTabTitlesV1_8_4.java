package net.rayfall.eyesniper2.skrayfall.v1_8_4;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.lang.reflect.Field;

public class EffTabTitlesV1_8_4 extends Effect {

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
        ChatSerializer.a("{\"text\": \"" + coreHeader.getSingle(evt).replace("\"", "") + "\"}");
    IChatBaseComponent footer =
        ChatSerializer.a("{\"text\": \"" + coreFooter.getSingle(evt).replace("\"", "") + "\"}");
    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
    try {
      Field headerField = packet.getClass().getDeclaredField("a");
      headerField.setAccessible(true);
      headerField.set(packet, header);
      headerField.setAccessible(!headerField.isAccessible());

      Field footerField = packet.getClass().getDeclaredField("b");
      footerField.setAccessible(true);
      footerField.set(packet, footer);
      footerField.setAccessible(!footerField.isAccessible());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    ((CraftPlayer) player.getSingle(evt)).getHandle().playerConnection.sendPacket(packet);

  }

}
