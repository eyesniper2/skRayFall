package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.aufdemrand.sentry.SentryInstance;
import net.aufdemrand.sentry.SentryTrait;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSentryStopFollow extends Effect{
	
	//make sentry %id% stop following
	private Expression<Number> id;
	

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
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
		SentryInstance st = npc.getTrait(SentryTrait.class).getInstance();
		st.setGuardTarget(null, false);
		
	}

}
