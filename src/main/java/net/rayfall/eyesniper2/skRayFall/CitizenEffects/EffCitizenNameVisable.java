package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenNameVisable extends Effect{
	
	private boolean vis;
	private Expression<Number> id;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean arg2,
			ParseResult arg3) {
		if (arg3.mark == 1){
			//invisible
			vis = false;
		}
		else{
			vis = true;
		}
		id = (Expression<Number>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "Invisability set on " + id.getSingle(arg0).intValue() + " is set to " + vis;
	}

	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npcName = registry.getById(id.getSingle(evt).intValue());
		if (vis == false){
			LivingEntity entity = (LivingEntity) npcName.getEntity();
	        entity.setCustomNameVisible(false);
	        npcName.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, entity.isCustomNameVisible());
		}
		else{
			LivingEntity entity = (LivingEntity) npcName.getEntity();
	        entity.setCustomNameVisible(true);
	        npcName.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, entity.isCustomNameVisible());
		}
		
	}

}
