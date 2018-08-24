package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.eclipse.jdt.annotation.Nullable;

@Name("Delete Group Score")
@Description("Deletes a group score.")
public class EffDeleteIdScore extends Effect {

    // (delete|remove) score [with] id %string%

    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        String usedId = id.getSingle(evt).replace("\"", "");
        if (Core.sbManager.getScore(usedId) != null) {
            Score score = Core.sbManager.getScore(usedId);
            Objective obj = score.getObjective();
            obj.getScoreboard().resetScores(score.getEntry());
            Core.sbManager.deleteScoreId(usedId);
        }
    }
}
