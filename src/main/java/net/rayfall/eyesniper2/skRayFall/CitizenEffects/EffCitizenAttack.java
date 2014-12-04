package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import javax.annotation.Nullable;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenAttack extends Effect{
	
	private Expression<Number> id;
	private Expression<LivingEntity> toBeAttacked;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<Number>) e[0];
		toBeAttacked = (Expression<LivingEntity>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return "NPC id: " + id.getSingle(evt).intValue() + " is attacking " + toBeAttacked.getSingle(evt).toString();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC attacker = registry.getById(id.getSingle(evt).intValue());
		attacker.getNavigator().setTarget(toBeAttacked.getSingle(evt), true);
		
	}

}
