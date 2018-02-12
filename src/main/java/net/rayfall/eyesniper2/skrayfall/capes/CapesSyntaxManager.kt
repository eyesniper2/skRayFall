package net.rayfall.eyesniper2.skrayfall.capes

import ch.njol.skript.Skript
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.plugin.Plugin

class CapesSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {
    override fun registerSyntax() {
        // Capes
        if (plugin.server.pluginManager.isPluginEnabled("Capes")) {
            plugin.logger.info("Getting heroic cape bacon.")
            Skript.registerEffect(EffRemoveCape::class.java, "remove cape of %player%")
            Skript.registerEffect(EffWearCape::class.java, "make %player% wear cape %itemstack%")
        }
    }

}