package net.rayfall.eyesniper2.skrayfall.crackshot.effects

import ch.njol.skript.Skript
import ch.njol.skript.doc.Description
import ch.njol.skript.doc.Name
import ch.njol.skript.lang.Effect
import ch.njol.skript.lang.Expression
import ch.njol.skript.lang.SkriptParser
import ch.njol.util.Kleenean
import com.shampaggon.crackshot.CSUtility
import de.slikey.effectlib.effect.AtomEffect
import de.slikey.effectlib.util.DynamicLocation
import net.rayfall.eyesniper2.skrayfall.Core
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Event

@Name("Generate Crackshot Explosion")
@Description("Generate an explosion through Crackshot.")
class EffGenerateExplosion : Effect() {

    // (generate|create) an explosion for %player% at %location% for weapon %string%

    private var locationExpression: Expression<Location>? = null
    private var playerExpression: Expression<Player>? = null
    private var weaponStringExpression: Expression<String>? = null

    @Suppress("UNCHECKED_CAST")
    override fun init(exp: Array<Expression<*>?>, arg1: Int, arg2: Kleenean, arg3: SkriptParser.ParseResult): Boolean {
        playerExpression = exp[0] as Expression<Player>?
        locationExpression = exp[1] as Expression<Location>?
        weaponStringExpression = exp[2] as Expression<String>?
        return true
    }

    override fun toString(arg0: Event?, arg1: Boolean): String {
        return ""
    }

    override fun execute(evt: Event) {
        val location = locationExpression?.getSingle(evt)
        val player = playerExpression?.getSingle(evt)
        val weaponString = weaponStringExpression?.getSingle(evt)?.replace("\"", "")

        if (location == null)
        {
            Skript.warning("Location was null for Generate Crackshot Explosion")
            return
        }

        if (player == null)
        {
            Skript.warning("Player was null for Generate Crackshot Explosion")
            return
        }

        if (weaponString == null)
        {
            Skript.warning("The weapons string was null for Generate Crackshot Explosion")
            return
        }

        val cs = CSUtility();
        cs.generateExplosion(player, location, weaponString)
    }
}