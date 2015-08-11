package net.rayfall.eyesniper2.skRayFall.CitizenExpressions;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.Entity;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

/**
 * Huge thanks to FunnyGatt for supplying this class. You can find him here: http://dev.bukkit.org/profiles/funnygatt/
 * and here: https://github.com/Funnygatt 
 * 
 * Thanks for the help and continue being awesome!
 */

public class ExprGeneralCitizen extends SimpleExpression<Entity>{

	public static NPC npc = null;
	private Expression<Number> id;
	
	@Override
	public Class<? extends Entity> getReturnType() {
		return Entity.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		this.id = (Expression<Number>) e[0];
	    return true;
	}

	@Override
	public String toString(
			@org.eclipse.jdt.annotation.Nullable org.bukkit.event.Event evt,
			boolean arg1) {
		
		return null;
	}

	@Override
	@org.eclipse.jdt.annotation.Nullable
	protected Entity[] get(org.bukkit.event.Event evt) {
		
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		try{
			NPC npc = registry.getById(((Number)this.id.getSingle(evt)).intValue());
			return new Entity[] { npc.getEntity() };
		}
		catch(NullPointerException e){
			return null;
		}

	}
	


	}

