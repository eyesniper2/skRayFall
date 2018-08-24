package net.rayfall.eyesniper2.skrayfall.general.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Max Health")
@Description({"Set max health by:",
        "* Entity",
        "* Value",
        "This will change the maximum health of an entity"})
public class EffMaxHealth extends Effect {

    private Expression<LivingEntity> tar;
    private Expression<Number> num;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        tar = (Expression<LivingEntity>) exp[0];
        num = (Expression<Number>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {

        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (tar != null) {
            tar.getSingle(evt).setMaxHealth(num.getSingle(evt).doubleValue());
        }
    }
}
