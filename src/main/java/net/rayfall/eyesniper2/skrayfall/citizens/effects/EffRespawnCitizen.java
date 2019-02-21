package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Direction;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Respawn Citizen")
@Description({"Respawn citizens by:",
        "* ID",
        "* Direction",
        "* Location",
        "This will force an NPC to come back to life if it was previously killed or despawned by the " +
        "despawn citizen effect. This will not work if the citizen is already alive so it can't be used " +
        "to duplicate citizens"})
@RequiredPlugins("Citizens")
@Examples({"command /npcrespawn:",
        "\ttrigger:\n",
        "\t\trespawn citizen 0 at location of player"})
public class EffRespawnCitizen extends Effect {

    private Expression<Location> location;
    private Expression<Number> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exprs[0];
        location = Direction.combine((Expression<? extends Direction>) exprs[1],
                (Expression<? extends Location>) exprs[2]);
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Spawning NPC: " + id.getSingle(arg0).intValue() + " At location " + location.toString();
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC respawn = registry.getById(id.getSingle(evt).intValue());
        if (respawn.isSpawned()) {
            Skript.warning("This NPC is still alive!");
        } else {
            respawn.spawn(location.getSingle(evt));
        }
    }

}
