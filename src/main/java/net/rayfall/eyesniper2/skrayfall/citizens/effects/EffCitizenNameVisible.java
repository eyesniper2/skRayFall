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

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Citizen Nametag Visibility")
@Description({"Modify citizen nametags by:",
        "* ID",
        "* Invisibility",
        "* Visibility",
        "For if you want to toggle the visibility of names above the NPC's head"})
@RequiredPlugins("Citizens")
public class EffCitizenNameVisible extends Effect {

    private boolean vis;
    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int matchedPattern, Kleenean arg2, ParseResult arg3) {
        vis = arg3.mark != 1;
        id = (Expression<Number>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Invisibility set on " + id.getSingle(arg0).intValue() + " is set to " + vis;
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npcName = registry.getById(id.getSingle(evt).intValue());
        if (!vis) {
            LivingEntity entity = (LivingEntity) npcName.getEntity();
            entity.setCustomNameVisible(false);
            npcName.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, entity.isCustomNameVisible());
        } else {
            LivingEntity entity = (LivingEntity) npcName.getEntity();
            entity.setCustomNameVisible(true);
            npcName.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, entity.isCustomNameVisible());
        }

    }

}
