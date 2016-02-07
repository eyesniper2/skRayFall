package net.rayfall.eyesniper2.skRayFall.CoreProtect;

import java.util.List;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.block.Block;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondIsNaturalCP extends Condition{
	
	private Expression<Block> block;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		block = (Expression<Block>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event evt) {
		Block b = block.getSingle(evt);
		List<String[]> c = skRayFall.skCoreProtect.blockLookup(b, Integer.MAX_VALUE);
		if (c.isEmpty()){
			return true;
		}
		else{
			return false;
		}
		
	}

}
