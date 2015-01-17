package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffMaxHealth extends Effect{
	
	private Expression<LivingEntity> tar;
	private Expression<Number> num;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		tar = (Expression<LivingEntity>) e[0];
		num = (Expression<Number>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {

		return null;
	}

	@Override
	protected void execute(Event evt) {
		tar.getSingle(evt).setMaxHealth(num.getSingle(evt).doubleValue());
	}

}
