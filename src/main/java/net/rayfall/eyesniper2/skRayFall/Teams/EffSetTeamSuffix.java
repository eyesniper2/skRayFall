package net.rayfall.eyesniper2.skRayFall.Teams;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetTeamSuffix extends Effect{
	
	//set suffix of team %string% to %string%
	
	private Expression<String> team;
	private Expression<String> suffix;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		team = (Expression<String>) e[0];
		suffix = (Expression<String>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(team != null && suffix != null){
			String fSuffix = suffix.getSingle(evt).replace("\"", "");
			if(fSuffix.length() <= 16){
				skRayFall.teamManager.setSuffix(team.getSingle(evt).replace("\"", ""), fSuffix);
			}
			else{
				Skript.error("Suffix can not be greater than 16 characters. It's currently " + fSuffix.length());
			}
		}
		
	}

}
