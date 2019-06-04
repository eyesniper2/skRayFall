package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.LookClose;
import org.bukkit.event.Event;

@Name("Citizens Look Close Trait")
@Description("Allow citizens to look at the closest player automatically. " +
        "This effect is toggleable, so to disable this trait for a citizen run this effect again.")
@RequiredPlugins("Citizens")
public class EffRemoveLookCloseTrait extends Effect {

    // remove npc %number%['s] look close trait

    private Expression<Number> id;

    @SuppressWarnings("unchecked")
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
