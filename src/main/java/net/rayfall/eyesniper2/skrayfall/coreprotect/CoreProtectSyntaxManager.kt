package net.rayfall.eyesniper2.skrayfall.coreprotect

import ch.njol.skript.Skript
import net.coreprotect.CoreProtect
import net.coreprotect.CoreProtectAPI
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.plugin.Plugin

class CoreProtectSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    var skCoreProtect: CoreProtectAPI? = null

    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("CoreProtect")) {
            val coreProtect = (plugin.server.pluginManager.getPlugin("CoreProtect") as CoreProtect).api
            if (coreProtect.APIVersion() < 7) {
                skCoreProtect = coreProtect
                plugin.logger.info("Cooking bacon for the CoreProtect loggers.")
                Skript.registerCondition(CondIsNaturalCoreProtect::class.java, "%block% is natural")
                Skript.registerCondition(CondIsNotNaturalCoreProtect::class.java, "%block% is not natural")
            } else {
                plugin.logger.warning("Unable to get bacon for the CoreProtect loggers " +
                        "[Unsupported API version (${coreProtect.APIVersion()})].")
            }
        }
    }
}