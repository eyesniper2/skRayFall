package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;
import org.bukkit.event.Event;

public class EffRemoveLookCloseTrait extends Effect{

    // remove npc %number%['s] look close trait

    private Expression<Number> id;

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        id = (Expression<Number>) expressions[0];
        return true;
    }

    @Override
    protected void execute(Event event) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npc = registry.getById(id.getSingle(event).intValue());
        npc.removeTrait(LookClose.class);
    }

    @Override
    public String toString(Event event, boolean b) {
        return null;
    }
}
