package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenVulnerablity extends Effect{
	
	private Expression<Number> id;
	private boolean vun;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int matchedPattern, Kleenean arg2,
			ParseResult pR) {
		if (pR.mark == 1){
			//invun
			vun = false;
		}
		else{
			vun = true;
		}
		id = (Expression<Number>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return "NPC id: " + id.getSingle(evt).intValue() + "Is vun? " + vun
				;
	}

	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC npcName = registry.getById(id.getSingle(evt).intValue());
		if (vun == true){
			npcName.setProtected(false);
		}
		if (vun == false){
			npcName.setProtected(true);
		}
		
	}

}
