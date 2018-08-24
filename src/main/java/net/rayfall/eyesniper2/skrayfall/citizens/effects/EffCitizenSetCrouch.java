package net.rayfall.eyesniper2.skrayfall.citizens.effects;

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

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Make Citizen Crouch/Uncrouch/Stand")
@Description("Make a NPC/Citizen Crouch, Uncrouch or stand.")
@RequiredPlugins("Citizens")
public class EffCitizenSetCrouch extends Effect {

    // make citizen %number% crouch(0)
    // make citizen %number% (uncrouch|stand)(1)

    private boolean shouldCrouch;
    private Expression<Number> id;


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int ind, Kleenean arg2, ParseResult arg3) {
        shouldCrouch = ind == 0;
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
        NPC npc = registry.getById(id.getSingle(evt).intValue());
        if (npc != null && npc.getEntity().getType().equals(EntityType.PLAYER)) {
            if (shouldCrouch) {
                // Make crouch here
                ((Player) npc.getEntity()).setSneaking(true);
            } else {
                // Make uncrouch here
                ((Player) npc.getEntity()).setSneaking(false);
            }
        }

    }

}
