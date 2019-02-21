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
import net.citizensnpcs.api.ai.speech.SpeechContext;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Make Citizen Talk")
@Description({"Make citizens communicate with:",
        "* ID",
        "* Messages",
        "* Players",
        "This will make an NPC speak to a player with a custom message"})
@RequiredPlugins("Citizens")
@Examples({"command /npcspeak <integer> <text>:",
        "\ttrigger:",
        "\t\tmake citizen arg 1 say \"%arg 2%\" to player"})
public class EffCitizenSpeak extends Effect {

    private Expression<Number> id;
    private Expression<String> speak;
    private Expression<LivingEntity> target;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<Number>) exp[0];
        speak = (Expression<String>) exp[1];
        target = (Expression<LivingEntity>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return "Citizen " + id.getSingle(arg0).intValue() + " Said " + speak.getSingle(arg0) + " to "
                + target.getSingle(arg0);
    }

    @Override
    protected void execute(Event evt) {
        NPCRegistry registry = CitizensAPI.getNPCRegistry();
        NPC npcSpeak = registry.getById(id.getSingle(evt).intValue());
        SpeechContext sp =
                new SpeechContext(npcSpeak, speak.getSingle(evt).replace("\"", ""), target.getSingle(evt));
        npcSpeak.getDefaultSpeechController().speak(sp);
    }

}
