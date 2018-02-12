package net.rayfall.eyesniper2.skrayfall.bossbarapi

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.plugin.Plugin

class BossBarApiSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {
    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("BossBarAPI")) {
            plugin.logger.info("Making BossBar Bacon.")
            Skript.registerEffect(EffTimedBossBar::class.java,
                    "display bossbar with %string% to %player% for %timespan%")
            Skript.registerEffect(EffCreateBossBar::class.java,
                    "set bossbar named %string% for %player% to %number%")
            Skript.registerEffect(EffDeleteBossBar::class.java, "delete bossbar of %player%")
            Skript.registerExpression(ExprBaseBossBar::class.java, Number::class.java, ExpressionType.PROPERTY,
                    "bossbar of %player%")
        }
    }

}