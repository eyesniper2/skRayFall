package net.rayfall.eyesniper2.skRayFall.Teams;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetTeamPrefix extends Effect{
	
	//set prefix of team %string% to %string%
	
	private Expression<String> team;
	private Expression<String> pre;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		team = (Expression<String>) e[0];
		pre = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(team != null && pre != null){
			String fPre = pre.getSingle(evt).replace("\"", "");
			if(fPre.length() <= 16){
				skRayFall.teamManager.setPrefix(team.getSingle(evt).replace("\"", ""), fPre);
			}
			else{
				Skript.error("Prefix can not be greater than 16 characters. It's currently " + fPre.length());
			}
		}
		
	}

}
