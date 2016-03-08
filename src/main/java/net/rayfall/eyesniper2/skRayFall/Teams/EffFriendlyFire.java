package net.rayfall.eyesniper2.skRayFall.Teams;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffFriendlyFire extends Effect{
	
	//set (friendly fire|ff) for team %string% to %boolean%
	
	private Expression<Boolean> ff;
	private Expression<String> team;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		team = (Expression<String>) e[0];
		ff = (Expression<Boolean>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(ff != null && team != null){
			skRayFall.teamManager.setFriendlyFire(team.getSingle(evt).replace("\"", ""), ff.getSingle(evt));
		}
		
	}

}
