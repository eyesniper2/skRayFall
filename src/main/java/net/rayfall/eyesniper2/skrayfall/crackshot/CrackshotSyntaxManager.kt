package net.rayfall.eyesniper2.skrayfall.crackshot

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import com.shampaggon.crackshot.events.*
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import net.rayfall.eyesniper2.skrayfall.crackshot.effects.EffGenerateExplosion
import net.rayfall.eyesniper2.skrayfall.crackshot.effects.EffPlaceMine
import net.rayfall.eyesniper2.skrayfall.crackshot.events.EvtScope
import net.rayfall.eyesniper2.skrayfall.crackshot.events.EvtUnscope
import net.rayfall.eyesniper2.skrayfall.crackshot.expressions.ExprCrackShotWeapon
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.eclipse.jdt.annotation.Nullable

class CrackshotSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("CrackShot")) {
            plugin.logger.info("Giving CrackShot snipers bacon.")
            Skript.registerExpression<ExprCrackShotWeapon, ItemStack>(ExprCrackShotWeapon::class.java, ItemStack::class.java, ExpressionType.SIMPLE,
                    "(gun|crackshot weapon) %string%")
            Skript.registerEffect<EffPlaceMine>(EffPlaceMine::class.java,
                    "(place|set|spawn) mine at %location% for %player% as %string%")
            Skript.registerEffect(EffGenerateExplosion::class.java,
                    "(generate|create) an explosion for %player% at %location% for weapon %string%")
            Skript.registerEvent("(crackshot|weapon|gun) shoot", SimpleEvent::class.java,
                    WeaponShootEvent::class.java, "(crackshot|weapon|gun) shoot")
            EventValues.registerEventValue(WeaponShootEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponShootEvent>() {
                        @Nullable
                        override fun get(evt: WeaponShootEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponShootEvent::class.java, Entity::class.java,
                    object : Getter<Entity, WeaponShootEvent>() {
                        @Nullable
                        override fun get(evt: WeaponShootEvent): Entity {
                            return evt.projectile
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponShootEvent::class.java, String::class.java,
                    object : Getter<String, WeaponShootEvent>() {
                        @Nullable
                        override fun get(evt: WeaponShootEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            Skript.registerEvent("(crackshot|weapon|gun) damage", SimpleEvent::class.java,
                    WeaponDamageEntityEvent::class.java, "(crackshot|weapon|gun) damage")
            EventValues.registerEventValue(WeaponDamageEntityEvent::class.java, String::class.java,
                    object : Getter<String, WeaponDamageEntityEvent>() {
                        @Nullable
                        override fun get(evt: WeaponDamageEntityEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponDamageEntityEvent::class.java, Entity::class.java,
                    object : Getter<Entity, WeaponDamageEntityEvent>() {
                        @Nullable
                        override fun get(evt: WeaponDamageEntityEvent): Entity {
                            return evt.victim
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponDamageEntityEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponDamageEntityEvent>() {
                        @Nullable
                        override fun get(evt: WeaponDamageEntityEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponDamageEntityEvent::class.java, String::class.java,
                    object : Getter<String, WeaponDamageEntityEvent>() {
                        @Nullable
                        override fun get(evt: WeaponDamageEntityEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            Skript.registerEvent<EvtScope>("Scope", EvtScope::class.java, WeaponScopeEvent::class.java,
                    "[crackshot|gun|weapon] scope [in]")
            Skript.registerEvent<EvtUnscope>("Unscope", EvtUnscope::class.java, WeaponScopeEvent::class.java,
                    "[crackshot|gun|weapon] unscope")
            EventValues.registerEventValue(WeaponScopeEvent::class.java, String::class.java,
                    object : Getter<String, WeaponScopeEvent>() {
                        @Nullable
                        override fun get(evt: WeaponScopeEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponScopeEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponScopeEvent>() {
                        @Nullable
                        override fun get(evt: WeaponScopeEvent): Player {
                            return evt.player
                        }
                    }, 0)
            Skript.registerEvent("(crackshot|weapon|gun) reload", SimpleEvent::class.java,
                    WeaponReloadEvent::class.java, "(crackshot|weapon|gun) reload")
            EventValues.registerEventValue(WeaponReloadEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponReloadEvent>() {
                        @Nullable
                        override fun get(evt: WeaponReloadEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponReloadEvent::class.java, String::class.java,
                    object : Getter<String, WeaponReloadEvent>() {
                        @Nullable
                        override fun get(evt: WeaponReloadEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponReloadEvent::class.java, Number::class.java,
                    object : Getter<Number, WeaponReloadEvent>() {
                        @Nullable
                        override fun get(evt: WeaponReloadEvent): Number {
                            return evt.reloadDuration
                        }
                    }, 0)
            Skript.registerEvent("(crackshot|weapon|gun) reload (finish|complete)", SimpleEvent::class.java,
                    WeaponReloadCompleteEvent::class.java, "(crackshot|weapon|gun) reload (finish|complete)")
            EventValues.registerEventValue(WeaponReloadCompleteEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponReloadCompleteEvent>() {
                        @Nullable
                        override fun get(evt: WeaponReloadCompleteEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponReloadCompleteEvent::class.java, String::class.java,
                    object : Getter<String, WeaponReloadCompleteEvent>() {
                        @Nullable
                        override fun get(evt: WeaponReloadCompleteEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            Skript.registerEvent("(crackshot|weapon|gun) hit block", SimpleEvent::class.java,
                    WeaponHitBlockEvent::class.java, "(crackshot|weapon|gun) hit block")
            EventValues.registerEventValue(WeaponHitBlockEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponHitBlockEvent>() {
                        @Nullable
                        override fun get(evt: WeaponHitBlockEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponHitBlockEvent::class.java, Block::class.java,
                    object : Getter<Block, WeaponHitBlockEvent>() {
                        @Nullable
                        override fun get(evt: WeaponHitBlockEvent): Block {
                            return evt.block
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponHitBlockEvent::class.java, String::class.java,
                    object : Getter<String, WeaponHitBlockEvent>() {
                        @Nullable
                        override fun get(evt: WeaponHitBlockEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            Skript.registerEvent("(before|prepare) (crackshot|weapon|gun) shoot", SimpleEvent::class.java,
                    WeaponPrepareShootEvent::class.java, "(before|prepare) (crackshot|weapon|gun) shoot")
            EventValues.registerEventValue(WeaponPrepareShootEvent::class.java, Player::class.java,
                    object : Getter<Player, WeaponPrepareShootEvent>() {
                        @Nullable
                        override fun get(evt: WeaponPrepareShootEvent): Player {
                            return evt.player
                        }
                    }, 0)
            EventValues.registerEventValue(WeaponPrepareShootEvent::class.java, String::class.java,
                    object : Getter<String, WeaponPrepareShootEvent>() {
                        @Nullable
                        override fun get(evt: WeaponPrepareShootEvent): String {
                            return evt.weaponTitle
                        }
                    }, 0)
            // Skript.registerEvent("weapon explosion", SimpleEvent.class,
            // WeaponExplodeEvent.class,"(crackshot|weapon) (explode|explosion)");
            // Skript.registerEvent("landmine explode", SimpleEvent.class,
            // WeaponTriggerEvent.class,"[crackshot] landmine explode");
        }
    }

}