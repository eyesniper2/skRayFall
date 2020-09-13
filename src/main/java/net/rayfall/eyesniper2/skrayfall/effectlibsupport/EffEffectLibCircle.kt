package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.CircleEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Circle Effect")
@Description("Creates an EffectLib circle effect.")
class EffEffectLibCircle : Effect() {

    // (spawn|create|apply) (a|the|an) circle (effect|formation) (at|on|for) %entity/location% with id
    // %string% [with particle[s] %-effectlibparticle%] [(and|with) radius %-number%]

    private var targetExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null
    private var particleExpression: Expression<VisualEffect>? = null
    private var radiusExpression: Expression<Number>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        targetExpression = exp[0]
        idExpression = exp[1] as Expression<String>?
        particleExpression = exp[2] as? Expression<VisualEffect>?
        radiusExpression = exp[3] as Expression<Number>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val particle = EffectLibUtils.getParticleFromVisualEffect(particleExpression?.getSingle(evt))
        val radius = radiusExpression?.getSingle(evt)
        val baseEffect = CircleEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Circle")
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
        if (radius != null) {
            baseEffect.radius = radius.toFloat()
        }

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}