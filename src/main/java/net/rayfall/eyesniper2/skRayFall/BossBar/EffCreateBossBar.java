package net.rayfall.eyesniper2.skRayFall.BossBar;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateBossBar extends Effect{
	//set bossbar named %string% for %player% to %number% 
	private Expression<String> t;
	private Expression<Player> player;
	private Expression<Number> per;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		t = (Expression<String>) e[0];
		player = (Expression<Player>) e[1];
		per = (Expression<Number>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		BossBarAPI.setMessage(player.getSingle(evt), t.getSingle(evt).toString().replace("\"", ""), per.getSingle(evt).floatValue());
	}

}
