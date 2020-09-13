package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.LineEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Line Effect")
@Description("Creates a EffectLib line effect.")
class EffEffectLibLine : Effect() {

    // (spawn|create|apply) (a|the|an) line (effect|formation) from %entity/location% to
    // %entity/location% with id %string% [with particle[startObject] %-effectlibparticle%]

    private var startExpression: Expression<*>? = null
    private var endExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null
    private var particleExpression: Expression<VisualEffect>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        startExpression = exp[0]
        endExpression = exp[1]
        idExpression = exp[2] as Expression<String>?
        particleExpression = exp[3] as? Expression<VisualEffect>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val startObject = startExpression?.getSingle(evt)
        val endObject = endExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val particle = EffectLibUtils.getParticleFromVisualEffect(particleExpression?.getSingle(evt))
        val baseEffect = LineEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Line")
            return
        }

        when (startObject) {
            is Entity -> {
                baseEffect.setDynamicOrigin(DynamicLocation(startObject as Entity?))
            }
            is Location -> {
                baseEffect.setDynamicOrigin(DynamicLocation(startObject as Location?))
            }
            else -> {
                assert(false)
            }
        }

        when (endObject) {
            is Entity -> {
                baseEffect.setDynamicTarget(DynamicLocation(endObject as Entity?))
            }
            is Location -> {
                baseEffect.setDynamicTarget(DynamicLocation(endObject as Location?))
            }
            else -> {
                assert(false)
            }
        }

        if (particle != null) {
            baseEffect.particle = particle
        }

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}