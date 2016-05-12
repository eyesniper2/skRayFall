package net.rayfall.eyesniper2.skRayFall.BossBarAPI;

import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

public class EffTimedBossBar extends Effect{
	//display bossbar with "text" to player for %timespan%
	private Expression<Player> player;
	private Expression<String> text;
	private Expression<Timespan> t;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		text = (Expression<String>) e[0];
		player = (Expression<Player>) e[1];
		t = (Expression<Timespan>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		BossBarAPI.setMessage(player.getSingle(evt), text.getSingle(evt).toString().replace("\"", ""), 100, t.getSingle(evt).getTicks()/20);
		//BossBarAPI.addBar(player.getSingle(evt), new TextComponent(text.getSingle(evt).replace("\"", "")),null,null, 1, t.getSingle(evt).getTicks()/20,5);
	}

}
