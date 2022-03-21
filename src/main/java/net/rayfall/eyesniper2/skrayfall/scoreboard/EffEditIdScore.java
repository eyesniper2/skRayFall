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

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Edit ID based score")
@Description("Edit an ID based score.")
public class EffEditIdScore extends Effect {

    // (edit|update) score [with] id %string% to %string% and %number%

    private Expression<String> id;
    private Expression<Number> slot;
    private Expression<String> newName;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        newName = (Expression<String>) exp[1];
        slot = (Expression<Number>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        String testId = id.getSingle(evt).replace("\"", "");
        if (Core.sbManager.getScore(testId) != null) {
            Core.sbManager.updateSingleScore(testId,
                    Utils.replaceChatStyles(newName.getSingle(evt).replace("\"", "")),
                    slot.getSingle(evt).intValue());
        } else {
            Skript.error("The score id " + testId + " does not exist!");
        }
    }
}
