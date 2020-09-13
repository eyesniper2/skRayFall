package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.AnimatedBallEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Animated Ball Effect")
@Description("Creates an EffectLib animated ball effect.")
class EffEffectLibAnimatedBallEffect : Effect() {

    // (spawn|create|apply) (a|the|an) animated ball (effect|formation) (at|on|for|to)
    // %entity/location% with id %string% [with particle %visualeffects%][ off set by %number%,
    // %number%(,| and) %number%]

    private var targetExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null
    private var visualEffectExpression: Expression<VisualEffect>? = null
    private var xOffsetExpression: Expression<Number>? = null
    private var yOffsetExpression: Expression<Number>? = null
    private var zOffsetExpression: Expression<Number>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        targetExpression = exp[0]
        idExpression = exp[1] as Expression<String>
        visualEffectExpression = exp[2] as? Expression<VisualEffect>
        xOffsetExpression = exp[3] as Expression<Number>
        yOffsetExpression = exp[4] as Expression<Number>
        zOffsetExpression = exp[5] as Expression<Number>
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val baseEffect = AnimatedBallEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Animated Ball")
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

        val particle = EffectLibUtils.getParticleFromVisualEffect(visualEffectExpression?.getSingle(evt))

        if (particle != null) {
            baseEffect.particle = particle
        }

        val xOffset = xOffsetExpression?.getSingle(evt)
        val yOffset = yOffsetExpression?.getSingle(evt)
        val zOffset = zOffsetExpression?.getSingle(evt)

        if (xOffset != null && yOffset != null && zOffset != null) {
            baseEffect.xOffset = xOffset.toFloat()
            baseEffect.yOffset = yOffset.toFloat()
            baseEffect.zOffset = zOffset.toFloat()
        }

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}