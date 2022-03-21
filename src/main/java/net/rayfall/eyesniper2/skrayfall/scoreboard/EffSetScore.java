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
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Sidebar Score")
@Description({"Set score of a sidebar by:",
        "* Scoreboard",
        "* Name",
        "* Value",
        "* Player",
        "This will change the score of a score in a player's sidebar or create" +
                " a new score if it does not exist"})
public class EffSetScore extends Effect {

    private Expression<String> name;
    private Expression<Player> player;
    private Expression<Number> num;

    // set score %string% in sidebar of %player% to %number%

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        name = (Expression<String>) exp[0];
        player = (Expression<Player>) exp[1];
        num = (Expression<Number>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player == null || num == null || player.getSingle(evt).getScoreboard() == null) {
            Skript.error("This player is either not online or has yet to have a scoreboard set for them");
            return;
        }
        Scoreboard sb = player.getSingle(evt).getScoreboard();
        Objective objective = sb.getObjective(DisplaySlot.SIDEBAR);
        Score score;
        if (name.getSingle(evt) == null){
            Skript.warning("First arg in \"set score %string% in sidebar of %player% to %number%\" was null. " +
                    "Objective will now be named null.");
            score = objective.getScore("null");
        } else {
            score = objective.getScore(Utils.replaceChatStyles(name.getSingle(evt).replace("\"", "")));
        }
        score.setScore(num.getSingle(evt).intValue());
    }
}
