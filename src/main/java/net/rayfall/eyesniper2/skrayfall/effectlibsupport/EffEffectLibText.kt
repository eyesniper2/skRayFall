package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.skript.util.VisualEffect
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.TextEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.Particle
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Text Effect")
@Description("Creates an EffectLib text effect.")
@Examples("command /texteffect:",
        "\ttrigger:",
        "\t\tapply the text effect with text \"Text Effect\" as Redstone to player with id \"texteffecttest\"")
class EffEffectLibText : Effect() {

    // create a text (effect|formation) with text %string% as %visualeffects% (at|on|for) %% with
    // id %string% [and %number% large]

    private var targetExpression: Expression<*>? = null
    private var textExpression: Expression<String>? = null
    private var particleExpression: Expression<VisualEffect>? = null
    private var idExpression: Expression<String>? = null
    private var sizeExpression: Expression<Number?>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        textExpression = exp[0] as Expression<String>?
        particleExpression = exp[1] as Expression<VisualEffect>?
        targetExpression = exp[2]
        idExpression = exp[3] as Expression<String>?
        sizeExpression = exp[4] as Expression<Number?>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val text = textExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val particle = EffectLibUtils.getParticleFromVisualEffect(particleExpression?.getSingle(evt))
        val size = sizeExpression?.getSingle(evt)

        val baseEffect = TextEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Text")
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
        } else {
            Skript.warning("Particle was null for Effect Lib Text")
            return
        }

        if (text != null) {
            baseEffect.text = text
        } else {
            Skript.warning("Text was null for Effect Lib Text")
            return
        }

        if (size != null) {
            baseEffect.size = size.toFloat()
        }

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}