package net.rayfall.eyesniper2.skrayfall

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import ch.njol.skript.util.Version
import net.rayfall.eyesniper2.skrayfall.bossbar.*
import net.rayfall.eyesniper2.skrayfall.general.conditions.CondIsGlowing
import net.rayfall.eyesniper2.skrayfall.general.effects.EffMakePlayerGlow
import net.rayfall.eyesniper2.skrayfall.general.effects.EffUnglowPlayer
import net.rayfall.eyesniper2.skrayfall.general.events.ArmorStandDamageEvent
import net.rayfall.eyesniper2.skrayfall.general.events.ArmorStandListener
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprNoNbt
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprRayfallOffhand
import net.rayfall.eyesniper2.skrayfall.general.expressions.ExprShinyItem
import net.rayfall.eyesniper2.skrayfall.v1_10.EffActionBarV1_10
import net.rayfall.eyesniper2.skrayfall.v1_10.EffParticlesV1_10
import net.rayfall.eyesniper2.skrayfall.v1_10.EffTabTitlesV1_10
import net.rayfall.eyesniper2.skrayfall.v1_10.EffTitleV1_10
import net.rayfall.eyesniper2.skrayfall.v1_11.EffActionBarV1_11
import net.rayfall.eyesniper2.skrayfall.v1_11.EffParticlesV1_11
import net.rayfall.eyesniper2.skrayfall.v1_11.EffTabTitlesV1_11
import net.rayfall.eyesniper2.skrayfall.v1_11.EffTitleV1_11
import net.rayfall.eyesniper2.skrayfall.v1_12.EffActionBarV1_12
import net.rayfall.eyesniper2.skrayfall.v1_12.EffParticlesV1_12
import net.rayfall.eyesniper2.skrayfall.v1_12.EffTabTitlesV1_12
import net.rayfall.eyesniper2.skrayfall.v1_12.EffTitleV1_12
import net.rayfall.eyesniper2.skrayfall.v1_13.EffActionBarV1_13
import net.rayfall.eyesniper2.skrayfall.v1_13.EffParticlesV1_13
import net.rayfall.eyesniper2.skrayfall.v1_13.EffTabTitlesV1_13
import net.rayfall.eyesniper2.skrayfall.v1_13.EffTitleV1_13
import net.rayfall.eyesniper2.skrayfall.v1_13_1.EffActionBarV1_13_1
import net.rayfall.eyesniper2.skrayfall.v1_13_1.EffParticlesV1_13_1
import net.rayfall.eyesniper2.skrayfall.v1_13_1.EffTabTitlesV1_13_1
import net.rayfall.eyesniper2.skrayfall.v1_13_1.EffTitleV1_13_1
import net.rayfall.eyesniper2.skrayfall.v1_14.EffActionBarV1_14
import net.rayfall.eyesniper2.skrayfall.v1_14.EffParticlesV1_14
import net.rayfall.eyesniper2.skrayfall.v1_14.EffTabTitlesV1_14
import net.rayfall.eyesniper2.skrayfall.v1_14.EffTitleV1_14
import net.rayfall.eyesniper2.skrayfall.v1_15.EffActionBarV1_15
import net.rayfall.eyesniper2.skrayfall.v1_15.EffParticlesV1_15
import net.rayfall.eyesniper2.skrayfall.v1_15.EffTabTitlesV1_15
import net.rayfall.eyesniper2.skrayfall.v1_15.EffTitleV1_15
import net.rayfall.eyesniper2.skrayfall.v1_16.EffActionBarV1_16
import net.rayfall.eyesniper2.skrayfall.v1_16.EffParticlesV1_16
import net.rayfall.eyesniper2.skrayfall.v1_16.EffTabTitlesV1_16
import net.rayfall.eyesniper2.skrayfall.v1_16.EffTitleV1_16
import net.rayfall.eyesniper2.skrayfall.v1_16_2.EffActionBarV1_16_2
import net.rayfall.eyesniper2.skrayfall.v1_16_2.EffParticlesV1_16_2
import net.rayfall.eyesniper2.skrayfall.v1_16_2.EffTabTitlesV1_16_2
import net.rayfall.eyesniper2.skrayfall.v1_16_2.EffTitleV1_16_2
import net.rayfall.eyesniper2.skrayfall.v1_16_3.EffActionBarV1_16_3
import net.rayfall.eyesniper2.skrayfall.v1_16_3.EffParticlesV1_16_3
import net.rayfall.eyesniper2.skrayfall.v1_16_3.EffTabTitlesV1_16_3
import net.rayfall.eyesniper2.skrayfall.v1_16_3.EffTitleV1_16_3
import net.rayfall.eyesniper2.skrayfall.v1_17.EffActionBarV1_17
import net.rayfall.eyesniper2.skrayfall.v1_17.EffParticlesV1_17
import net.rayfall.eyesniper2.skrayfall.v1_17.EffTabTitlesV1_17
import net.rayfall.eyesniper2.skrayfall.v1_17.EffTitleV1_17
import net.rayfall.eyesniper2.skrayfall.v1_8.EffActionBarV1_8
import net.rayfall.eyesniper2.skrayfall.v1_8.EffParticlesV1_8
import net.rayfall.eyesniper2.skrayfall.v1_8.EffTabTitlesV1_8
import net.rayfall.eyesniper2.skrayfall.v1_8.EffTitleV1_8
import net.rayfall.eyesniper2.skrayfall.v1_8_3.EffActionBarV1_8_3
import net.rayfall.eyesniper2.skrayfall.v1_8_3.EffParticlesV1_8_3
import net.rayfall.eyesniper2.skrayfall.v1_8_3.EffTabTitlesV1_8_3
import net.rayfall.eyesniper2.skrayfall.v1_8_3.EffTitleV1_8_3
import net.rayfall.eyesniper2.skrayfall.v1_8_4.EffActionBarV1_8_4
import net.rayfall.eyesniper2.skrayfall.v1_8_4.EffParticlesV1_8_4
import net.rayfall.eyesniper2.skrayfall.v1_8_4.EffTabTitlesV1_8_4
import net.rayfall.eyesniper2.skrayfall.v1_8_4.EffTitleV1_8_4
import net.rayfall.eyesniper2.skrayfall.v1_9.EffActionBarV1_9
import net.rayfall.eyesniper2.skrayfall.v1_9.EffParticlesV1_9
import net.rayfall.eyesniper2.skrayfall.v1_9.EffTabTitlesV1_9
import net.rayfall.eyesniper2.skrayfall.v1_9.EffTitleV1_9
import net.rayfall.eyesniper2.skrayfall.v1_9_4.EffActionBarV1_9_4
import net.rayfall.eyesniper2.skrayfall.v1_9_4.EffParticlesV1_9_4
import net.rayfall.eyesniper2.skrayfall.v1_9_4.EffTabTitlesV1_9_4
import net.rayfall.eyesniper2.skrayfall.v1_9_4.EffTitleV1_9_4
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.eclipse.jdt.annotation.Nullable

class VersionedGeneralSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    val bossbarManager: BossBarManager = BossBarManager()

    override fun registerSyntax() {
        if (Skript.isRunningMinecraft(1, 8)) {
            plugin.logger.info("Enabling general 1.8+ bacon!")
            Skript.registerExpression(ExprShinyItem::class.java, ItemStack::class.java, ExpressionType.PROPERTY,
                    "shiny %itemstacks%")
            Skript.registerExpression(ExprNoNbt::class.java, ItemStack::class.java, ExpressionType.PROPERTY,
                    "%itemstacks% with no nbt")
            ArmorStandListener(plugin)
            Skript.registerEvent("armorstand damage", SimpleEvent::class.java, ArmorStandDamageEvent::class.java,
                    "armo[u]r stand damage")
            EventValues.registerEventValue(ArmorStandDamageEvent::class.java, Entity::class.java,
                    object : Getter<Entity, ArmorStandDamageEvent>() {
                        @Nullable
                        override fun get(evt: ArmorStandDamageEvent): Entity {
                            return evt.armorStand
                        }
                    }, 0)
            EventValues.registerEventValue(ArmorStandDamageEvent::class.java, Player::class.java,
                    object : Getter<Player, ArmorStandDamageEvent>() {
                        @Nullable
                        override fun get(evt: ArmorStandDamageEvent): Player {
                            return evt.damager
                        }
                    }, 0)
        }
        if (Skript.getMinecraftVersion().compareTo(Version(1, 8)) == 0) {
            plugin.logger.info("Getting all the special 1.8 bacon!")
            Skript.registerEffect(EffTitleV1_8::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_8::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_8::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_8::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion().compareTo(Version(1, 8, 3)) == 0) {
            plugin.logger.info("Getting the extra special 1.8.3 bacon!")
            Skript.registerEffect(EffTitleV1_8_3::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_8_3::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_8_3::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_8_3::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 8, 4) && Skript.getMinecraftVersion() <= Version(1, 8, 8)) {
            plugin.logger.info("Getting the extra special 1.8.4 - 1.8.8 bacon!")
            Skript.registerEffect(EffTitleV1_8_4::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_8_4::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_8_4::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_8_4::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.isRunningMinecraft(1, 9)) {
            plugin.logger.info("Getting the general 1.9+ bacon!")
            // New bossbar content
            Skript.registerEffect(EffCreateModernBossBar::class.java,
                    "create (bossbar|boss bar) title[d] %string% and id %string% for %players% "
                            + "[with (value|progress) %number%] [with colors %-bossbarcolor%] "
                            + "[with style %-bossbarstyle%] [with flags %-bossbarflag%]")
            Skript.registerEffect(EffDeleteModernBossBar::class.java, "(remove|destroy) bossbar %string%")
            Skript.registerEffect(EffRemovePlayerFromBossBar::class.java,
                    "remove %players% [from] bossbar %string%")
            Skript.registerEffect(EffSetBossBar::class.java,
                    "(add|set) bossbar %string% for %player% ",
                    "(add|give) %players% [to] bossbar %string%")
            Skript.registerEffect(EffChangeBossBarValue::class.java,
                    "(set|edit) bossbar %string% (value|progress) to %number%")
            Skript.registerEffect(EffChangeBossBarColor::class.java,
                    "(set|edit) bossbar %string% colo[u]r to %bossbarcolor%")
            Skript.registerEffect(EffChangeBossBarTitle::class.java,
                    "(set|edit) bossbar %string% (title|name) to %string%")
            Skript.registerEffect(EffChangeBossBarStyle::class.java,
                    "(set|edit) bossbar %string% style to %bossbarstyle%")
            Skript.registerEffect(EffBossBarAddFlag::class.java,
                    "(add|set) [a] [the] flag %bossbarflag% to [the] bossbar %string%")
            Skript.registerEffect(EffBossBarRemoveFlag::class.java,
                    "(remove|delete) [a] [the] flag %bossbarflag% [to] [from] [the] bossbar %string%")
            Skript.registerEffect(EffBossBarShow::class.java, "(show|display|unhide) bossbar %string%")
            Skript.registerEffect(EffBossBarHide::class.java, "hide bossbar %string%")
            Skript.registerExpression(ExprBossBarTitle::class.java, String::class.java, ExpressionType.SIMPLE,
                    "(title|name) of bossbar %string%")
            Skript.registerExpression(ExprBossBarValue::class.java, Number::class.java, ExpressionType.SIMPLE,
                    "(value|progress) of bossbar %string%")
            // Glowing API
            Skript.registerEffect(EffMakePlayerGlow::class.java, "make %player% glow")
            Skript.registerEffect(EffUnglowPlayer::class.java, "make %player% (unglow|stop glowing)")
            Skript.registerCondition(CondIsGlowing::class.java,"%player% glowing")
            // 1.9.2 Team stuff
            // Skript.registerEffect(Eff1_9MessageOnDeathRule.class,
            // "(show|display) death message[s] %teamoptionstatus% for team %string%");
            // Skript.registerEffect(Eff1_9NameTagVisibility.class,
            // "(show|display) (name tags|nametags) %teamoptionstatus% for team %string%");
            // Skript.registerEffect(Eff1_9TeamCollisionRule.class,
            // "(set|define) team collision [rule] as %teamoptionstatus% for team %string%");
            Skript.registerExpression(ExprRayfallOffhand::class.java, ItemStack::class.java, ExpressionType.SIMPLE,
                    "%player%['s] offhand", "item in %player%['s] offhand")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 9, 0) && Skript.getMinecraftVersion() <= Version(1, 9, 3)) {
            plugin.logger.info("Getting the extra special 1.9 - 1.9.3 bacon!")
            Skript.registerEffect(EffTitleV1_9::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_9::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_9::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_9::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion().compareTo(Version(1, 9, 4)) == 0) {
            plugin.logger.info("Getting the extra special 1.9.4 bacon!")
            Skript.registerEffect(EffTitleV1_9_4::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_9_4::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_9_4::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_9_4::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 10, 0) && Skript.getMinecraftVersion() <= Version(1, 10, 3)) {
            plugin.logger.info("Getting the extra special 1.10 - 1.10.2 bacon!")
            Skript.registerEffect(EffTitleV1_10::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_10::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_10::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_10::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 11, 0) && Skript.getMinecraftVersion() < Version(1, 12, 0)) {
            plugin.logger.info("Getting the extra special 1.11 bacon!")
            Skript.registerEffect(EffTitleV1_11::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_11::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_11::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_11::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 12, 0) && Skript.getMinecraftVersion() < Version(1, 13, 0)) {
            plugin.logger.info("Getting the extra special 1.12 bacon!")
            Skript.registerEffect(EffTitleV1_12::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_12::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_12::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_12::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 13, 0) && Skript.getMinecraftVersion() < Version(1, 13, 1)) {
            plugin.logger.info("Getting the extra special 1.13 bacon!")
            Skript.registerEffect(EffTitleV1_13::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_13::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_13::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_13::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 13, 1) && Skript.getMinecraftVersion() < Version(1, 14)) {
            plugin.logger.info("Getting the extra special 1.13.1 bacon!")
            Skript.registerEffect(EffTitleV1_13_1::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_13_1::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_13_1::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_13_1::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 14, 0) && Skript.getMinecraftVersion() < Version(1, 14, 5)) {
            plugin.logger.info("Getting the extra special 1.14 - 1.14.4 bacon!")
            Skript.registerEffect(EffTitleV1_14::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_14::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_14::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_14::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 15, 0) && Skript.getMinecraftVersion() < Version(1, 15, 3)) {
            plugin.logger.info("Getting the extra special 1.15 - 1.15.2 bacon!")
            Skript.registerEffect(EffTitleV1_15::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_15::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_15::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_15::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 16, 0) && Skript.getMinecraftVersion() < Version(1, 16, 2)) {
            plugin.logger.info("Getting the extra special 1.16 - 1.16.2 bacon!")
            Skript.registerEffect(EffTitleV1_16::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_16::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_16::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_16::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 16, 2) && Skript.getMinecraftVersion() < Version(1, 16, 4)) {
            plugin.logger.info("Getting the extra special 1.16.2 - 1.16.4 bacon!")
            Skript.registerEffect(EffTitleV1_16_2::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_16_2::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_16_2::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_16_2::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 16, 4) && Skript.getMinecraftVersion() < Version(1, 16, 6)) {
            plugin.logger.info("Getting the extra special 1.16.4 bacon!")
            Skript.registerEffect(EffTitleV1_16_3::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_16_3::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_16_3::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_16_3::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
        if (Skript.getMinecraftVersion() >= Version(1, 17, 0)) {
            plugin.logger.info("Getting the extra special 1.17+ bacon!")
            Skript.registerEffect(EffTitleV1_17::class.java,
                    "send %players% title %string% [with subtitle %-string%] [for %-timespan%] " + "[with %-timespan% fade in and %-timespan% fade out]")
            Skript.registerEffect(EffParticlesV1_17::class.java,
                    "show %number% %string% particle[s] at %location% for %player% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffActionBarV1_17::class.java, "set action bar of %players% to %string%",
                    "set %player%['s] action bar to %string%")
            Skript.registerEffect(EffTabTitlesV1_17::class.java,
                    "set tab header to %string% and footer to %string% for %player%")
        }
    }
}