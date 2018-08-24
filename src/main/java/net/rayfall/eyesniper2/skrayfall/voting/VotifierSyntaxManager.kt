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
                    .description("Check server vote by:",
                            "* Player",
                            "* Website",
                            "This will be called when someone votes for the server. " +
                            "Use event-string to return the name of the website they voted on")
                    .requiredPlugins("Votifier")
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
                    .description("Check server vote by:",
                            "* Player",
                            "* Website",
                            "This will be called whenever a vote is received by the server but the player is offline." +
                            "Once the player rejoins the On Vote event will be triggered. Use event-string to return " +
                            "the name of the website they voted on")
                    .requiredPlugins("Votifier")
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
                    .description("Check server vote by:",
                            "* Player",
                            "* Website",
                            "This will be called whenever a vote is received by the server regardless if the player " +
                            "is online. Use event-string to return the name of the website they voted on")
                    .requiredPlugins("Votifier")
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