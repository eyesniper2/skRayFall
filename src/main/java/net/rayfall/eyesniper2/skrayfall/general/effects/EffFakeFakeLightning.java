package net.rayfall.eyesniper2.skrayfall.general.effects;


import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Fake Lightning (No Sound)")
@Description("Creates a silent fake lightning strike at a location.")
public class EffFakeFakeLightning extends Effect {

    // create [fake|ultra|no sound] fake lighting at %location%

    private Expression<Location> loc;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        loc = (Expression<Location>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (loc.getSingle(evt) != null) {
            loc.getSingle(evt).getWorld().spigot().strikeLightningEffect(loc.getSingle(evt), true);
        }
    }
}
