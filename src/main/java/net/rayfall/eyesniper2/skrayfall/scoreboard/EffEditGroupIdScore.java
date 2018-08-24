package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

// (edit|update) score [with] (group|id) %string% to %string% and %number%

@Name("Update Group Score")
@Description("Updates a group based score.")
public class EffEditGroupIdScore extends Effect {

    Expression<String> id;
    private Expression<String> newName;
    private Expression<Number> newScore;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        newName = (Expression<String>) exp[1];
        newScore = (Expression<Number>) exp[2];

        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (id != null && newName != null && newScore != null) {
            Core.sbManager.groupUpdateScore(id.getSingle(evt).replace("\"", ""),
                    newName.getSingle(evt).replace("\"", ""), newScore.getSingle(evt).intValue());
        }
    }
}
