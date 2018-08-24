package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.eclipse.jdt.annotation.Nullable;

@Name("Wipe Below Name")
@Description({"Wipe score below player by:",
        "* Scoreboard",
        "* Player",
        "This will wipe the score below the player"})
public class EffRemoveScoreBelowName extends Effect {
    // (wipe|erase|delete) score below %player%

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player.getSingle(evt) == null) {
            Skript.error("The player is not online!");
        } else {
            if (player.getSingle(evt).getScoreboard().getObjective("bottomHold") != null) {
                player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.BELOW_NAME).unregister();
            } else {
                Skript.error("That player doesnt have a scoreboard under their name!");
            }
        }
    }
}
