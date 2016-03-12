package net.rayfall.eyesniper2.skRayFall.BossBar;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffChangeBossBarColor extends Effect{
	
	//(set|edit) bossbar %string% colo[u]r to %bossbarcolor% 
	
	private Expression<String> id;
	private Expression<skRayFallBarColor> c;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		c = (Expression<skRayFallBarColor>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		skRayFall.bossbarManager.changeColor(id.getSingle(evt).replace("\"", ""), c.getSingle(evt).getKey());
	}

}
