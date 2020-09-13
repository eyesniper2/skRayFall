package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.TornadoEffect
import de.slikey.effectlib.effect.WaveEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Tornado Effect")
@Description("Creates an EffectLib tornado effect.")
class EffEffectTornado : Effect() {

    // (spawn|create|apply) (a|the|an) tornado (effect|formation) at %entity/location% with id
    // %string% [with tornado particle[s] %visualeffects% and cloud particle[s]
    // %visualeffects%] [(set|and) radius %number%] [(set|and) max height %number%]

    private var targetExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null
    private var tornadoParticleExpression: Expression<VisualEffect>? = null
    private var cloudParticleExpression: Expression<VisualEffect>? = null
    private var radiusExpression: Expression<Number?>? = null
    private var heightExpression: Expression<Number?>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        targetExpression = exp[0]
        idExpression = exp[1] as Expression<String>?
        tornadoParticleExpression = exp[2] as? Expression<VisualEffect>?
        cloudParticleExpression = exp[3] as? Expression<VisualEffect>?
        radiusExpression = exp[4] as Expression<Number?>?
        heightExpression = exp[5] as Expression<Number?>?
        return true
    }

    override fun toString(evt: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val tornadoParticle = EffectLibUtils.getParticleFromVisualEffect(tornadoParticleExpression?.getSingle(evt))
        val cloudParticle = EffectLibUtils.getParticleFromVisualEffect(cloudParticleExpression?.getSingle(evt))
        val radius = radiusExpression?.getSingle(evt)
        val height = heightExpression?.getSingle(evt)

        val baseEffect = TornadoEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Tornado")
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

        if (tornadoParticle != null) {
            baseEffect.tornadoParticle = tornadoParticle
        }

        if (cloudParticle != null) {
            baseEffect.cloudParticle = cloudParticle
        }

        if (height != null) {
            baseEffect.tornadoHeight = height.toFloat()
        }

        if (radius != null) {
            baseEffect.maxTornadoRadius = radius.toFloat()
        }

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}