package net.rayfall.eyesniper2.skRayFall.CitizenExpressions;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.jrbudda.builder.BuilderTrait;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTopLeftSchematic extends SimpleExpression<Location>{
	
	private Expression<Number> id;
	private Expression<Location> loc;
	
	//for builder %number% get the location of the top left of schematic centered at %location%

	@Override
	public Class<? extends Location> getReturnType() {
		return Location.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<Number>) e[0];
		loc = (Expression<Location>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Location[] get(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.getById(id.getSingle(evt).intValue());
		BuilderTrait bt = npc.getTrait(BuilderTrait.class);
		if (bt.schematic != null){
			Location topLeft = loc.getSingle(evt).add((Math.round(bt.schematic.width() / 2)), bt.schematic.height() - 1, (Math.round(bt.schematic.length() / 2)));
			return new Location[] {topLeft};
		}
		else{
			Skript.error("A schematic has yet to be loaded for this Builder");
			return new Location[] {loc.getSingle(evt)};
		}
	}

}
