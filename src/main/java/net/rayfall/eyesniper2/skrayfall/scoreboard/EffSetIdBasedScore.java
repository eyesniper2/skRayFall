package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Utils;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set ID based Score")
@Description("Make an ID based score in a players scoreboard. This method is ideal for " +
        "creating complex text based scoreboards.")
public class EffSetIdBasedScore extends Effect {

    // (set|create) id [based] score %string% in sidebar of %player% to %number% with id %string%

    private Expression<String> name;
    private Expression<Player> player;
    private Expression<Number> num;
    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        name = (Expression<String>) exp[0];
        player = (Expression<Player>) exp[1];
        num = (Expression<Number>) exp[2];
        id = (Expression<String>) exp[3];
        return true;
    }


    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player == null || num == null || name == null
                || player.getSingle(evt).getScoreboard() == null) {
            Skript.error("This player is either not online or has yet to have a scoreboard set for them");
        } else {
            Scoreboard sb = player.getSingle(evt).getScoreboard();
            Objective objective = sb.getObjective(DisplaySlot.SIDEBAR);
            Score score = objective.getScore(Utils.replaceChatStyles(name.getSingle(evt).replace("\"", "")));
            score.setScore(num.getSingle(evt).intValue());
            Core.sbManager.setScoreId(id.getSingle(evt).replace("\"", ""), score, player.getSingle(evt));
        }
    }
}
