package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Sentry as Passive, Neutral or Hostile")
@Description("Set a sentry's status as passive, neutral or hostile")
@RequiredPlugins("Citizens")
public class EffSentryPna extends Effect {
    // set citizen <number> to <passive/neutral/hostile>
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        return false;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {

    }

}
