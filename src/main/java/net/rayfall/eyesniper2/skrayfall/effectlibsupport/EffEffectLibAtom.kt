package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.util.Kleenean
import ch.njol.skript.lang.SkriptParser
import de.slikey.effectlib.effect.AtomEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.event.Event

@Name("Atom Effect")
@Description("Creates an EffectLib atom effect.")
class EffEffectLibAtom : Effect() {

    // (spawn|create|apply) (a|the|an) atom (effect|formation) (at|on|for) %entity/location% with id
    // %string%

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
        val baseEffect = AtomEffect(Core.effectManager)

        if (id == null)
        {
            Skript.warning("Id was null for EffectLib Atom")
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