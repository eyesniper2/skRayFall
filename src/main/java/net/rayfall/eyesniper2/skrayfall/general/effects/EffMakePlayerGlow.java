package net.rayfall.eyesniper2.skrayfall.general.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Make Player Glow")
@Description("Make a player glow. This is based on team color, will be white by default.")
@Examples({"command /playerglow:",
        "\ttrigger:",
        "\t\tmake player glow",
        "\t\twait 5 seconds",
        "\t\tmake player stop glowing"})
public class EffMakePlayerGlow extends Effect {

    // Make %player% glow

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player != null && player.getSingle(evt) != null) {
            player.getSingle(evt).setGlowing(true);
        }
    }
}
