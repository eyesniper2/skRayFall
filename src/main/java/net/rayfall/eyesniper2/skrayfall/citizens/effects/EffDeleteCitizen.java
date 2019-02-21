package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Remove Citizen")
@Description({"Remove citizens by:",
        "* ID",
        "This will permanently remove a citizen"})
@RequiredPlugins("Citizens")
@Examples({"command /removelastnpc:",
        "\ttrigger:",
        "\t\tdestroy citizen last created citizen"})
public class EffDeleteCitizen extends Effect {

    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
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
        if (id != null && registry.getById(id.getSingle(evt).intValue()) != null) {
            NPC delete = registry.getById(id.getSingle(evt).intValue());
            if (delete != null && delete.getOwningRegistry() != null) {
                delete.destroy();
            }
        }
    }
}
