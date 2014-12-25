package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenMove extends Effect{
	private Expression<Number> idNum;
	private Expression<Number> speed;
	private Expression<Location> location;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2,
			ParseResult arg3) {
		idNum = (Expression<Number>) exprs[0];
		location = (Expression<Location>) exprs[1];
		speed = (Expression<Number>) exprs[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "Citizens move, Id Number: " + idNum.getSingle(arg0).intValue() + " To loaction: " + location.toString();
	}

	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.getById(idNum.getSingle(evt).intValue());
		Location moveTo = location.getSingle(evt);
		if (speed != null){
			npc.getNavigator().getDefaultParameters().baseSpeed(speed.getSingle(evt).floatValue());
		}
		npc.getNavigator().setTarget(moveTo);
	}

}
