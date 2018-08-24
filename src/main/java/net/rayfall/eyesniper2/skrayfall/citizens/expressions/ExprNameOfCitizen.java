package net.rayfall.eyesniper2.skrayfall.citizens.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Name of Citizen")
@Description({"Used for:",
        "* Checking citizen names",
        "* Changing citizen names",
        "This will allow you to check and set an NPC's name"})
@RequiredPlugins("Citizens")
public class ExprNameOfCitizen extends SimpleExpression<String> {

    private Expression<Number> id;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

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
    @Nullable
    protected String[] get(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npcName = registry.getById(id.getSingle(evt).intValue());
        return new String[]{npcName.getName()};
    }

}
