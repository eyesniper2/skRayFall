package net.rayfall.eyesniper2.skrayfall.general.conditions

import ch.njol.skript.lang.Condition
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import org.bukkit.entity.Player
import org.bukkit.event.Event

class CondIsGlowing : Condition() {

    lateinit var player: Expression<Player>

    override fun check(e: Event?): Boolean {
        return if (player.getSingle(e) != null) {
            player.getSingle(e)!!.isGlowing
        } else false
    }

    override fun toString(e: Event?, debug: Boolean): String {
        return ""
    }

    override fun init(exprs: Array<out Expression<*>>?, matchedPattern: Int, isDelayed: Kleenean?, parseResult: SkriptParser.ParseResult?): Boolean {
        player = exprs!![0] as Expression<Player>
        return true
    }
}