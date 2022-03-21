package net.rayfall.eyesniper2.skrayfall.v1_17;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Utils;
import ch.njol.util.Kleenean;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import net.md_5.bungee.api.chat.TextComponent;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Action Bar")
@Description({"Display Action Bar by:",
        "* Player",
        "* Text",
        "This will display text above the player's hotbar for a few seconds. " +
                "This is like the text you receive when changing the item in your hand"})
@DocumentationId("EffActionBar")
public class EffActionBarV1_17 extends Effect {

    // set action bar of %player% to %string%

    private Expression<Player> player;
    private Expression<String> text;

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
        String message = Utils.replaceChatStyles(text.getSingle(evt));
        for (Player player : player.getAll(evt)) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        }
    }
}