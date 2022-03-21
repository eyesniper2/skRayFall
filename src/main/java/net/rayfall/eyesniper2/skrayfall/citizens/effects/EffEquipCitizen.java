package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Equipment;
import net.citizensnpcs.api.trait.trait.Equipment.EquipmentSlot;

import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

@Name("Equip Citizen")
@Description({"Give citizens:",
        "* Tools",
        "* Armo(u)r",
        "Give citizens things to hold in their hands and wear"})
@RequiredPlugins("Citizens")
public class EffEquipCitizen extends Effect {

    private Expression<Number> id;
    private Expression<ItemStack> item;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int matchedPattern, Kleenean arg2, ParseResult arg3) {
        item = (Expression<ItemStack>) exp[1];
        id = (Expression<Number>) exp[0];
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
        EquipmentSlot slot = EquipmentSlot.HAND;
        switch (item.getSingle(evt).getType()) {
            case LEATHER_BOOTS:
            case IRON_BOOTS:
            case GOLDEN_BOOTS:
            case CHAINMAIL_BOOTS:
            case DIAMOND_BOOTS:
                slot = EquipmentSlot.BOOTS;
                break;
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case DIAMOND_LEGGINGS:
                slot = EquipmentSlot.LEGGINGS;
                break;
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case CHAINMAIL_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
                slot = EquipmentSlot.CHESTPLATE;
                break;
            case LEATHER_HELMET:
            case IRON_HELMET:
            case GOLDEN_HELMET:
            case CHAINMAIL_HELMET:
            case DIAMOND_HELMET:
                slot = EquipmentSlot.HELMET;
                break;
            default:
                break;

        }
        if (getter.getEntity().getType().equals(EntityType.PLAYER)
                || getter.getEntity().getType() == EntityType.ENDERMAN
                || getter.getEntity().getType() == EntityType.ZOMBIE
                || getter.getEntity().getType() == EntityType.SKELETON) {
            Equipment equ = getter.getTrait(Equipment.class);
            equ.set(slot, item.getSingle(evt));
        } else {
            Skript.error("Entity must be equipable!");
        }
    }

}
