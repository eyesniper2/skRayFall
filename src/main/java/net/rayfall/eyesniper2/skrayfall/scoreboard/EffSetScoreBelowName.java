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
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Below Name Score")
@Description({"Set score of a sidebar by:",
        "* Name",
        "* Value",
        "* Player",
        "* Target",
        "This will change the name and score below a player's name"})
public class EffSetScoreBelowName extends Effect {
    // set score %string% below %player% to %number% for %player%
    // First player is score target, second player is who is seeing it

    private Expression<Player> player;
    private Expression<Player> targets;
    private Expression<String> name;
    private Expression<Number> num;


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[1];
        name = (Expression<String>) exp[0];
        num = (Expression<Number>) exp[2];
        targets = (Expression<Player>) exp[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (!(player.getSingle(evt).isOnline())) {
            Skript.error("[SetScoreBelowName] The player is not online!");
        } else if (num.getSingle(evt) == null){
            Skript.error("[SetScoreBelowName] The scoreboard value is not set!");
        } else if (name.getSingle(evt) == null){
            Skript.error("[SetScoreBelowName] The scoreboard name is not set!");
        }
        else {
            int value = num.getSingle(evt).intValue();
            String displayName = Utils.replaceChatStyles(name.getSingle(evt).replace("\"", ""));
            for(Player target : targets.getAll(evt)){
                if (target.getScoreboard().getObjective("bottomHold") != null) {
                    Objective objective =
                            target.getScoreboard().getObjective(DisplaySlot.BELOW_NAME);
                    objective.setDisplayName(displayName);
                    Score score = objective.getScore(player.getSingle(evt).getName());
                    score.setScore(value);
                } else {
                    Objective objectiveh =
                            target.getScoreboard().registerNewObjective("bottomHold", "dummy");
                    objectiveh.setDisplaySlot(DisplaySlot.BELOW_NAME);
                    objectiveh.setDisplayName(displayName);
                    Score score = objectiveh.getScore(player.getSingle(evt).getName());
                    score.setScore(value);
                }
            }
        }
    }
}
