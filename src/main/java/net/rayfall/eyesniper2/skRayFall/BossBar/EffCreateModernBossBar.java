package net.rayfall.eyesniper2.skRayFall.BossBar;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateModernBossBar extends Effect{
	
	//create (bossbar|boss bar) title[d] %string% and id %string% for %players% [with (value|progress) %number%] [with colors %-bossbarcolors%] [with style %-bossbarstyle%] [with flags %-bossbarflags%]
	
	private Expression<String> id;
	private Expression<String> title;
	private Expression<Number> value;
	private Expression<skRayFallBarColor> color;
	private Expression<skRayFallBarFlag> flag;
	private Expression<skRayFallBarStyle> style;
	private Expression<Player> players;


	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		title = (Expression<String>) e[0];
		value = (Expression<Number>) e[3];
		id = (Expression<String>) e[1];
		players = (Expression<Player>) e[2];
		color = (Expression<skRayFallBarColor>) e[4];
		style = (Expression<skRayFallBarStyle>) e[5];
		flag = (Expression<skRayFallBarFlag>) e[6];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		BarStyle s = BarStyle.SOLID;
		BarColor c = BarColor.PURPLE;
		if (style != null) {
			s = style.getSingle(evt).getKey();
		}
		if (color != null) {
			c = color.getSingle(evt).getKey();
		}
		BossBar bar = null;
		if (flag != null) {
			bar = Bukkit.createBossBar(title.getSingle(evt).replace("\"", ""), c, s, flag.getSingle(evt).getKey());
		}
		else{
			bar = Bukkit.createBossBar(title.getSingle(evt).replace("\"", ""), c, s);
		}
		if(value != null){
			double v = value.getSingle(evt).doubleValue();
			if(v>100){
				v = 100;
			}
			else if(v<0){
				v = 0;
			}
			bar.setProgress(v/100);
		}
		for (Player p : players.getAll(evt)) {
			bar.addPlayer(p);
		}
		skRayFall.bossbarManager.createBossBar(
				id.getSingle(evt).replace("\"", ""), bar);

	}
	
	

}
