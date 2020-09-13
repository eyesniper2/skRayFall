package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import de.slikey.effectlib.effect.BleedEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Bleed Effect")
@Description("Creates an EffectLib bleed effect.")
@Examples("command /bleedtest:",
        "\ttrigger:",
        "\t\tcreate a bleed effect to player with id \"test\"",
        "\t\twait 10 seconds",
        "\t\tmessage \"&cDelete\"",
        "\t\tdelete effect \"test\"")
class EffEffectLibBleed : Effect() {

    // (spawn|create|apply) (a|the|an) bleed (effect|formation) (at|on|for|to) %entity/location% with
    // id %string%

    private var targetExpression: Expression<*>? = null
    private var idExpression: Expression<String>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        targetExpression = exp[0]
        idExpression = exp[1] as Expression<String>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val target = targetExpression?.getSingle(evt)
        val id = idExpression?.getSingle(evt)
        val baseEffect = BleedEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Bleed")
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

        baseEffect.infinite()
        baseEffect.start()

        val setEffectSuccess = Core.rayfallEffectManager.setEffect(baseEffect, id.replace("\"", ""))
        if (!setEffectSuccess) {
            baseEffect.cancel()
        }
    }
}