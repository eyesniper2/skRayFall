package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.util.PlayerAnimation;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenSleep extends Effect{
	
	//(make|force) (npc|citizen) with id %number% to sleep     type = 0
	//(make|force) (npc|citizen) with id %number% to wake up   type = 1
	
	Expression<Number> id;
	boolean type;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int t, Kleenean arg2,
			ParseResult arg3) {
		if(t == 0){
			type = false;
		}
		else{
			type = true;
		}
		id = (Expression<Number>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npc = registry.getById(id.getSingle(evt).intValue());
		if(npc != null && npc.getEntity().getType().equals(EntityType.PLAYER)){
			if(type){
				PlayerAnimation.STOP_SLEEPING.play((Player)npc.getEntity());
			}
			else{
				PlayerAnimation.SLEEP.play((Player)npc.getEntity());
			}
		}
		
	}

}
