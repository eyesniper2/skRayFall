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

@Name("Add/Set Bossbar Flag")
@Description("Add a flag to a ID based Bossbar.")
public class EffBossBarAddFlag extends Effect {

    // (add|set)[a|the] flag %skrayfallbossbarflags% to bossbar %string%

    private Expression<RayFallBarFlag> flag;
    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        flag = (Expression<RayFallBarFlag>) exp[0];
        id = (Expression<String>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Core.bossbarManager.addFlag(id.getSingle(evt).replace("\"", ""), flag.getSingle(evt).getKey());
    }
}
