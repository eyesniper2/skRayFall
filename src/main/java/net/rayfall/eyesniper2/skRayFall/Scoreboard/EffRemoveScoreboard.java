package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRemoveScoreboard extends Effect{
//(wipe|erase) %player%['s] sidebar
	
	private Expression<Player> player;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {

		player = (Expression<Player>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (player.getSingle(evt).getScoreboard().getObjective("sidebarHold") != null){
			skRayFall.sbManager.removeScoreFromPlayer(player.getSingle(evt));
			player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.SIDEBAR).unregister();
		}
		else{
			return;
		}
		
		
		
	}
	
	

}
