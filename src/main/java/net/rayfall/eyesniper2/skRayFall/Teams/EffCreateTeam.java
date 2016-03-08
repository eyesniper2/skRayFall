package net.rayfall.eyesniper2.skRayFall.Teams;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCreateTeam extends Effect{
	
	//(create|make) [a] team named %string% [with %players%]
	
	private Expression<String> name;
	private Expression<Player> players;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		name = (Expression<String>) e[0];
		players = (Expression<Player>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		TeamManager tm = skRayFall.teamManager;
		if(name != null){
			String tname = name.getSingle(evt).replace("\"", "");
			if(players != null){
				tm.createTeam(tname, players.getAll(evt));
			}
			else{
				tm.createTeam(tname);
			}
		}
		
	}

}
