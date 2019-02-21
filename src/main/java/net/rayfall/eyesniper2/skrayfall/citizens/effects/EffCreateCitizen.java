package net.rayfall.eyesniper2.skrayfall.citizens.effects;

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
import net.rayfall.eyesniper2.skrayfall.citizens.expressions.ExprLastCitizen;
import net.rayfall.eyesniper2.skrayfall.utli.ScrubEntityType;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;

@Name("Create Citizen")
@Description({"Create citizens with:",
        "* Name",
        "* Direction",
        "* Location",
        "* Entity Type",
        "This will place an NPC in your world that can be modified"})
@RequiredPlugins("Citizens")
@Examples({"command /createwolf <text>:",
        "\ttrigger:",
        "\t\tcreate a citizen named \"%arg 1%\" at location of player as a wolf"})
public class EffCreateCitizen extends Effect {

    private Expression<Location> location;
    private Expression<String> name;
    private Expression<EntityType> type;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2, ParseResult arg3) {
        name = (Expression<String>) exprs[0];
        location = Direction.combine((Expression<? extends Direction>) exprs[1],
                (Expression<? extends Location>) exprs[2]);
        type = (Expression<EntityType>) exprs[3];
        return true;
    }

    @Override
    public String toString(Event evt, boolean arg1) {
        return "Created NPC named: " + name.toString(evt, arg1) + " Loaction: "
                + location.toString(evt, arg1) + " Type:" + type.toString();
    }

    @Override
    public void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        EntityType citizenType = ScrubEntityType.getType(type.toString());
        NPC npc = registry.createNPC(citizenType, name.getSingle(evt).toString().replace("\"", ""));
        Location spawnto = location.getSingle(evt);
        npc.spawn(spawnto);
        ExprLastCitizen.lastNPC = npc;

    }

}
