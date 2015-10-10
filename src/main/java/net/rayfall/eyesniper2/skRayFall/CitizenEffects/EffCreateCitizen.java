package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprLastCitizen;
import net.rayfall.eyesniper2.skRayFall.utli.ScrubEntityType;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.entity.EntityType;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Direction;
import ch.njol.util.Kleenean;

public class EffCreateCitizen extends Effect{

	private Expression<Location> location;
	private Expression<String> name;
	private Expression<EntityType> type;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2,
			ParseResult arg3) {
		name = (Expression<String>) exprs[0];
		location = Direction.combine((Expression<? extends Direction>) exprs[1], (Expression<? extends Location>) exprs[2]);
		type = (Expression<EntityType>) exprs[3];
		return true;
	}

	@Override
	public String toString(Event evt, boolean arg1) {
		return "Created NPC named: " + name.toString(evt, arg1) + " Loaction: " + location.toString(evt, arg1) + " Type:" + type.toString();
	}
	@Override
	public void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		EntityType cType = ScrubEntityType.getType(type.toString());
		NPC npc = registry.createNPC(cType, name.getSingle(evt).toString().replace("\"", ""));
		Location spawnto = location.getSingle(evt);
		npc.spawn(spawnto);
		ExprLastCitizen.lastNPC = npc;
		
	}
		
}


