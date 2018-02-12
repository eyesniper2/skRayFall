package net.rayfall.eyesniper2.skrayfall.voting

import ch.njol.skript.Skript
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.eclipse.jdt.annotation.Nullable

class VotifierSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {
    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("Votifier")) {
            plugin.logger.info("Getting more bacon for the Votifier runners!")
            RayFallVoteListener(plugin)
            Skript.registerEvent("On Vote", SimpleEvent::class.java, RayFallVoteEvent::class.java, "vote[ing]")
            EventValues.registerEventValue(RayFallVoteEvent::class.java, Player::class.java,
                    object : Getter<Player, RayFallVoteEvent>() {
                        @Nullable
                        override fun get(evt: RayFallVoteEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(RayFallVoteEvent::class.java, String::class.java,
                    object : Getter<String, RayFallVoteEvent>() {
                        @Nullable
                        override fun get(evt: RayFallVoteEvent): String {
                            return evt.site
                        }
                    }, 0)
            Skript.registerEvent("On Offline Vote", SimpleEvent::class.java, RayFallOfflineVoteEvent::class.java,
                    "offline vote[ing]")
            EventValues.registerEventValue(RayFallOfflineVoteEvent::class.java, Player::class.java,
                    object : Getter<Player, RayFallOfflineVoteEvent>() {
                        @Nullable
                        override fun get(evt: RayFallOfflineVoteEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(RayFallOfflineVoteEvent::class.java, String::class.java,
                    object : Getter<String, RayFallOfflineVoteEvent>() {
                        @Nullable
                        override fun get(evt: RayFallOfflineVoteEvent): String {
                            return evt.site
                        }
                    }, 0)
            Skript.registerEvent("On Vote Received", SimpleEvent::class.java, RayFallVoteReceivedEvent::class.java,
                    "[raw ]vote receiv(e|ed)")
            EventValues.registerEventValue(RayFallVoteReceivedEvent::class.java, String::class.java,
                    object : Getter<String, RayFallVoteReceivedEvent>() {
                        @Nullable
                        override fun get(evt: RayFallVoteReceivedEvent): String {
                            return evt.votersName
                        }
                    }, 0)
        } else {
            plugin.logger.info("No Votifier Found! *Checks oven for finished bacon*")
        }
    }

}