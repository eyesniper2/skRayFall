package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Value/Progress of Bossbar")
@Description("Get the value of an ID based Bossbar.")
public class ExprBossBarValue extends SimpleExpression<Number> {

    private Expression<String> id;

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
        return new Number[]{
                Core.bossbarManager.getBarProgress(id.getSingle(evt).replace("\"", "")).doubleValue() * 100};
    }
}
