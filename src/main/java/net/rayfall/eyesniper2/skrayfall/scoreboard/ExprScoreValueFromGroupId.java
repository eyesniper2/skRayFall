package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Group Score Value")
@Description("These expressions will get either the name or value of a group based score.")
@DocumentationId("GroupScoreValue")
public class ExprScoreValueFromGroupId extends SimpleExpression<Number> {

    // group score (value|number) (of|from) id %string%

    Expression<String> id;

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @Nullable
    protected Number[] get(Event evt) {
        return new Number[]{Core.sbManager.getValueOfGroupScore(id.getSingle(evt).replace("\"", ""))};
    }
}
