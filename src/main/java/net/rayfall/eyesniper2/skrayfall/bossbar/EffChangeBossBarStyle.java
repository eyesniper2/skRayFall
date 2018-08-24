package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Bossbar Style")
@Description("Set the style of an ID based Bossbar.")
public class EffChangeBossBarStyle extends Effect {

    // (set|edit) bossbar %string% style to %bossbarstyle%

    private Expression<RayFallBarStyle> style;
    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        style = (Expression<RayFallBarStyle>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Core.bossbarManager.changeStyle(id.getSingle(evt).replace("\"", ""),
                style.getSingle(evt).getKey());
    }
}
