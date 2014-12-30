package net.rayfall.eyesniper2.skRayFall.Scoreboard;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetScoreTab extends Effect{
	//set tab[list] score of %player% to %number% for %player%
	//first player is being changed, second is the one being shown it
	private Expression<Player> player;
	private Expression<Player> showing;
	private Expression<Number> num;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) e[0];
		showing = (Expression<Player>) e[2];
		num = (Expression<Number>) e[1];

		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {

		return null;
	}

	@Override
	protected void execute(Event evt) {
		if (!(showing.getSingle(evt).isOnline()) || !(player.getSingle(evt).isOnline()) ){
			Skript.error("The player is not online!");
		}
		else{
			if (showing.getSingle(evt).getScoreboard().getObjective("holdTab") != null){
				Objective objective = showing.getSingle(evt).getScoreboard().getObjective(DisplaySlot.PLAYER_LIST);
				Score score = objective.getScore(player.getSingle(evt).getDisplayName());
				score.setScore(num.getSingle(evt).intValue());
			}
			else{
				Objective objectiveh = showing.getSingle(evt).getScoreboard().registerNewObjective("holdTab", "dummy");
				objectiveh.setDisplaySlot(DisplaySlot.PLAYER_LIST);
				Score score = objectiveh.getScore(player.getSingle(evt).getDisplayName());
				score.setScore(num.getSingle(evt).intValue());
			}
			
		
		}
		
	}

}
