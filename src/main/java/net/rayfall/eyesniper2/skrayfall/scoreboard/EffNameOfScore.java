package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Utils;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Sidebar Name")
@Description({"Change the sidebar name by:",
        "* Scoreboard",
        "* Name",
        "* Players",
        "This will change the name of a player's sidebar. The sidebar of the player must have at " +
        "least one score in order for it to show on the sidebar"})
public class EffNameOfScore extends Effect {

    // set name of sidebar of %player% to %string%

    private Expression<Player> players;
    private Expression<String> name;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        players = (Expression<Player>) exp[0];
        name = (Expression<String>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {

        for (Player p : players.getArray(evt)) {
            if (!(p.isOnline())) {
                Skript.error("The player is not online!");
                continue;
            }
            try {
                if (p.getScoreboard().getObjective("sidebarHold") != null) {
                    Objective objective = p.getScoreboard().getObjective(DisplaySlot.SIDEBAR);
                    objective.setDisplayName(Utils.replaceChatStyles(name.getSingle(evt).replace("\"", "")));
                } else {
                    Objective objectiveh = p.getScoreboard().registerNewObjective("sidebarHold", "dummy");
                    objectiveh.setDisplaySlot(DisplaySlot.SIDEBAR);
                    objectiveh.setDisplayName(Utils.replaceChatStyles(name.getSingle(evt).replace("\"", "")));
                }
            } catch (IllegalArgumentException e){
                Skript.error(e.getLocalizedMessage());
            }
        }
    }
}
