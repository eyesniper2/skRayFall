package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Meta Data")
@Description("Get the meta data value for an entity")
public class ExprMetaData extends SimpleExpression<String> {

    // meta %string% for %entity%

    private Expression<String> ent;
    private Expression<Entity> target;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        ent = (Expression<String>) exp[0];
        target = (Expression<Entity>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @Nullable
    protected String[] get(Event evt) {
        String[] data = (String[]) target.getSingle(evt).getMetadata(ent.getSingle(evt)
                .replace("\"", "")).toArray();
        return data;
    }
}
