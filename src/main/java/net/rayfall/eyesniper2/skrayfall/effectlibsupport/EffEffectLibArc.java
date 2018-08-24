package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import de.slikey.effectlib.effect.ArcEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Arc Effect")
@Description("Creates an EffectLib arc effect.")
public class EffEffectLibArc extends Effect {

    // (spawn|create|apply) (a|the|an) arc (effect|formation) from %entity/location% to
    // %entity/location% with id %string% [with particle[s] %-effectlibparticle%] [%number% high] [and
    // %number% particles]

    private Particle test;
    private Expression<?> start;
    private Expression<?> end;
    private Expression<String> id;
    private Expression<ParticleEffect> particle;
    private Expression<Number> height;
    private Expression<Number> numOfParticles;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        start = exp[0];
        end = exp[1];
        id = (Expression<String>) exp[2];
        particle = (Expression<ParticleEffect>) exp[3];
        height = (Expression<Number>) exp[4];
        numOfParticles = (Expression<Number>) exp[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Object startObject = start.getSingle(evt);
        Object endObject = end.getSingle(evt);
        ArcEffect effect = new ArcEffect(Core.effectManager);
        if (startObject instanceof Entity) {
            effect.setDynamicOrigin(new DynamicLocation((Entity) startObject));
        } else if (startObject instanceof Location) {
            effect.setDynamicOrigin(new DynamicLocation((Location) startObject));
        } else {
            assert false;
        }
        if (endObject instanceof Entity) {
            effect.setDynamicTarget(new DynamicLocation((Entity) endObject));
        } else if (endObject instanceof Location) {
            effect.setDynamicTarget(new DynamicLocation((Location) endObject));
        } else {
            assert false;
        }
        if (particle != null) {
            effect.particle = particle.getSingle(evt);
        }
        if (height.getSingle(evt) != null) {
            effect.height = height.getSingle(evt).floatValue();
        }
        if (numOfParticles.getSingle(evt) != null) {
            effect.particles = numOfParticles.getSingle(evt).intValue();
        }
        effect.infinite();
        effect.start();
        Boolean check = Core.rayfallEffectManager.setEffect(effect, id.getSingle(evt).replace("\"", ""));
        if (!check) {
            effect.cancel();
        }
    }

}

