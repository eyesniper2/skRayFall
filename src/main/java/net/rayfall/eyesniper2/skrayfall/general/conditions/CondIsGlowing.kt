package net.rayfall.eyesniper2.skrayfall.general.conditions

import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.doc.Since
import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.entity.Player
import org.bukkit.event.Event

@Name("Is Glowing")
@Description("Check to see if a player is currently glowing")
@Since("1.9.13")
class CondIsGlowing : Condition() {

    lateinit var player: Expression<Player>

    override fun check(e: Event?): Boolean {
        player.getSingle(e)?.let {
            return it.isGlowing
        }
        return false
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(exprs: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parseResult: SkriptParser.ParseResult?): Boolean {
        player = exprs?.get(0) as Expression<Player>
        return true
    }
}