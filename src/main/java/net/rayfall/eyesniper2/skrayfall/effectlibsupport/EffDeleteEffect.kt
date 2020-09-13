package net.rayfall.eyesniper2.skrayfall.effectlibsupport

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Examples
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.event.Event

@Name("Delete/Stop Effect")
@Description("Stops a EffectLib effect that matches the id value.")
@Examples("command /bleedtest:",
        "\ttrigger:",
        "\t\tcreate a bleed effect to player with id \"bleedtest\"",
        "\t\twait 10 seconds",
        "\t\tmessage \"&cDelete\"",
        "\t\tdelete effect \"bleedtest\"")
class EffDeleteEffect : Effect() {

    // (delete|stop) (effect|formation) %string%
    private var idExpression: Expression<String>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        idExpression = exp[0] as Expression<String>
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val id = idExpression?.getSingle(evt)
        Core.rayfallEffectManager.deleteEffect(id?.replace("\"", ""))
    }
}