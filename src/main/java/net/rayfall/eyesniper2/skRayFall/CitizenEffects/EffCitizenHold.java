package net.rayfall.eyesniper2.skRayFall.CitizenEffects;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCitizenHold extends Effect{
	
	//make citizen %number% hold %itemstack%
	
	private Expression<Number> id;
	private Expression<ItemStack> item;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<Number>) e[0];
		item = (Expression<ItemStack>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		NPCRegistry registry = CitizensAPI.getNPCRegistry();
		NPC getter = registry.getById(id.getSingle(evt).intValue());
		if (getter.getEntity().getType().equals(EntityType.PLAYER) || getter.getEntity().getType() == EntityType.ENDERMAN || getter.getEntity().getType() == EntityType.ZOMBIE || getter.getEntity().getType() == EntityType.SKELETON){
			Equipment equ = getter.getTrait(Equipment.class);
			equ.set(EquipmentSlot.HAND, item.getSingle(evt));
		}
		else
			Skript.error("Entity must be equipable!");
		
	}

}
