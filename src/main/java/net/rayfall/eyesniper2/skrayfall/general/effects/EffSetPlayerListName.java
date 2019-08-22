package net.rayfall.eyesniper2.skrayfall.general.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Players Tab Name")
@Description("Lets you customize a players name within the tab menu. Please note the ['s] was added as of 1.9.18.")
public class EffSetPlayerListName extends Effect {
    private Expression<Player> player;
    private Expression<String> name;

    // set %players% tab name to %string%

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) expression[0];
        name = (Expression<String>) expression[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player.getSingle(evt) != null && player.getSingle(evt).isOnline()) {
            player.getSingle(evt).setPlayerListName(name.getSingle(evt).replace("\"", ""));
        }
    }
}
