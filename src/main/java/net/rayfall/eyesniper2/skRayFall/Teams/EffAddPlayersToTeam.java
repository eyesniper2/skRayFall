package net.rayfall.eyesniper2.skRayFall.Teams;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffAddPlayersToTeam extends Effect{
	
	//(add|place) %players% on [to] team %string%
	
	private Expression<Player> players;
	private Expression<String> team;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		players = (Expression<Player>) e[0];
		team = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(players != null && team != null){
			skRayFall.teamManager.addPlayesrToTeam(team.getSingle(evt).replace("\"", ""), players.getAll(evt));
		}
		
	}

}
