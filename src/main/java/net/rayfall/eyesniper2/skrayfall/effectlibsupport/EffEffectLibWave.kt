package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.WaveEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Wave Effect")
@Description("Creates an EffectLib wave effect.")
class EffEffectLibWave : Effect() {

    // (spawn|create|apply) (a|the|an) wave (effect|formation) at %entity/location% with id %string%
    // [with particle[s] %-effectlibparticle%]

    private var targetExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null
    private var particleExpression: Expression<VisualEffect>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        targetExpression = exp[0]
        idExpression = exp[1] as Expression<String>?
        particleExpression = exp[2] as? Expression<VisualEffect>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val particle = EffectLibUtils.getParticleFromVisualEffect(particleExpression?.getSingle(evt))
        val baseEffect = WaveEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Wave")
            return
        }

        when (target) {
            is Entity -> {
                baseEffect.setDynamicOrigin(DynamicLocation(target as Entity?))
            }
            is Location -> {
                baseEffect.setDynamicOrigin(DynamicLocation(target as Location?))
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