package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRemoveScoreTab extends Effect{
	//(wipe|erase|delete) %player%['s] tab[score]
	
	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (player.getSingle(evt) == null){
			Skript.error("The player is not online!");
		}
		else{
			if (player.getSingle(evt).getScoreboard().getObjective("holdTab") != null){
				player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.PLAYER_LIST).unregister();
			}
			else{
				Skript.error("That player doesn't have a tab score!");
			}
		}
		
	}

}
