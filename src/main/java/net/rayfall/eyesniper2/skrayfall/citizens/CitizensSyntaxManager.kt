package net.rayfall.eyesniper2.skrayfall.citizens

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.citizensnpcs.api.event.NPCDeathEvent
import net.citizensnpcs.api.event.NPCLeftClickEvent
import net.citizensnpcs.api.event.NPCRightClickEvent
import net.citizensnpcs.api.npc.NPC
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import net.rayfall.eyesniper2.skrayfall.citizens.conditions.CondIsNpc
import net.rayfall.eyesniper2.skrayfall.citizens.conditions.CondIsNpcId
import net.rayfall.eyesniper2.skrayfall.citizens.conditions.CondIsNpcIdGeneral
import net.rayfall.eyesniper2.skrayfall.citizens.conditions.CondIsNpcNamed
import net.rayfall.eyesniper2.skrayfall.citizens.effects.*
import net.rayfall.eyesniper2.skrayfall.citizens.expressions.*
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class CitizensSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("Citizens")) {
            plugin.logger.info("Getting more bacon for the army of citizens...")
            Skript.registerEffect(EffCreateCitizen::class.java,
                    "create [a] citizen named %string% (at|%direction%) %location% " + "[as (a|an) %entitytypes%]")
            Skript.registerEffect(EffCitizenMove::class.java,
                    "move citizen %number% to %location% [at speed %number%]")
            Skript.registerEffect(EffDespawnCitizen::class.java, "despawn citizen %number%")
            Skript.registerEffect(EffEquipCitizen::class.java,
                    "(equip|give) citizen %number% with [an] %itemstack%")
            Skript.registerEffect(EffCitizenHold::class.java, "make citizen %number% hold [an] %itemstack%")
            Skript.registerEffect(EffSpawnCitizen::class.java,
                    "respawn citizen %number% (at|%direction%) %location%")
            Skript.registerEffect(EffDeleteCitizen::class.java, "(remove|destroy) citizen %number%")
            Skript.registerEffect(EffCitizenSpeak::class.java,
                    "make citizen %number% (say|communicate) %string% to %livingentities%")
            Skript.registerEffect(EffSetCitizenName::class.java,
                    "(set|make) citizen[s] %number% name to %string%")
            Skript.registerEffect(EffCitizenSetMaxHealth::class.java,
                    "(set|make) citizen[s] %number% max health [to] %number%")
            // doesn't work
            Skript.registerEffect(EffCitizenNameVisable::class.java,
                    "(set|make) citizen[s] %number% nametag (1¦invisible|0¦visible)")
            Skript.registerEffect(EffCitizenLookTarget::class.java, "make citizen %number% look at %location%")
            Skript.registerEffect(EffCitizenAttack::class.java,
                    "make citizen %number% (attack|fight) %entity%")
            // buggy
            Skript.registerEffect(EffCitizenSetSkin::class.java, "change citizen %number% skin to %string%")
            Skript.registerEffect(EffGiveLookCloseTrait::class.java,
                    "(give|set) npc %number% the look close trait")
            Skript.registerEffect(EffRemoveLookCloseTrait::class.java,
                    "remove npc %number%['s] look close trait")
            Skript.registerEffect(EffCitizenVulnerablity::class.java,
                    "make citizen %number% (1¦invulnerable|0¦vulnerable)")
            Skript.registerEffect(EffCitizenSleep::class.java,
                    "(make|force) (npc|citizen) with id %number% to sleep",
                    "(make|force) (npc|citizen) with id %number% to wake [up]")
            Skript.registerEffect(EffCitizenSetCrouch::class.java,
                    "(set|make) citizen %number% [to] [be] crouch[ed]",
                    "(set|make) citizen %number% [to] [be] uncrouch[ed]")
            Skript.registerEffect(EffCitizenToggleCrouch::class.java, "toggle citizen %number%['s] crouch")
            Skript.registerEffect(EffCitizenSwing::class.java, "make citizen %number% swing [arm]")
            Skript.registerExpression(ExprLastCitizen::class.java, Number::class.java, ExpressionType.SIMPLE,
                    "last created citizen [id]")
            Skript.registerExpression(ExprNameOfCitizen::class.java, String::class.java, ExpressionType.SIMPLE,
                    "name of citizen %number%")
            Skript.registerExpression(ExprCitizenIdFromEntity::class.java, Number::class.java, ExpressionType.SIMPLE,
                    "citizen id of %entity%")
            Skript.registerExpression(ExprGeneralCitizen::class.java, Entity::class.java, ExpressionType.PROPERTY,
                    "citizen %number%")
            Skript.registerExpression(ExprOwnerOfCitizen::class.java, String::class.java, ExpressionType.PROPERTY,
                    "owner of (citizen|npc) %number%")
            Skript.registerEvent("NPC/Citizen Right Click", SimpleEvent::class.java, NPCRightClickEvent::class.java,
                    "(NPC|Citizen) right click")
            EventValues.registerEventValue(NPCRightClickEvent::class.java, Player::class.java,
                    object : Getter<Player, NPCRightClickEvent>() {
                        override fun get(event: NPCRightClickEvent): Player {
                            return event.clicker
                        }
                    }, 0)
            EventValues.registerEventValue(NPCRightClickEvent::class.java, NPC::class.java,
                    object : Getter<NPC, NPCRightClickEvent>() {
                        override fun get(event: NPCRightClickEvent): NPC {
                            return event.npc
                        }
                    }, 0)
            EventValues.registerEventValue(NPCRightClickEvent::class.java, Number::class.java,
                    object : Getter<Number, NPCRightClickEvent>() {
                        override fun get(event: NPCRightClickEvent): Number {
                            return event.npc.id
                        }
                    }, 0)
            Skript.registerEvent("NPC/Citizen Left Click", SimpleEvent::class.java, NPCLeftClickEvent::class.java,
                    "(NPC|Citizen) left click")
            EventValues.registerEventValue(NPCLeftClickEvent::class.java, Player::class.java,
                    object : Getter<Player, NPCLeftClickEvent>() {
                        override fun get(event: NPCLeftClickEvent): Player {
                            return event.clicker
                        }
                    }, 0)
            EventValues.registerEventValue(NPCLeftClickEvent::class.java, NPC::class.java,
                    object : Getter<NPC, NPCLeftClickEvent>() {
                        override fun get(event: NPCLeftClickEvent): NPC {
                            return event.npc
                        }
                    }, 0)
            EventValues.registerEventValue(NPCLeftClickEvent::class.java, Number::class.java,
                    object : Getter<Number, NPCLeftClickEvent>() {
                        override fun get(event: NPCLeftClickEvent): Number {
                            return event.npc.id
                        }
                    }, 0)
            Skript.registerEvent("NPC/Citizen Death", SimpleEvent::class.java, NPCDeathEvent::class.java,
                    "(NPC|Citizen) death")
            EventValues.registerEventValue(NPCDeathEvent::class.java, NPC::class.java,
                    object : Getter<NPC, NPCDeathEvent>() {
                        override fun get(event: NPCDeathEvent): NPC {
                            return event.npc
                        }
                    }, 0)
            Skript.registerCondition<CondIsNpcId>(CondIsNpcId::class.java, "(NPC|Citizen) is %number%")
            Skript.registerCondition<CondIsNpcIdGeneral>(CondIsNpcIdGeneral::class.java, "%entity% is (citizen|npc) %number%")
            Skript.registerCondition<CondIsNpcNamed>(CondIsNpcNamed::class.java,
                    "(NPC|Citizen)['s] [is] name[d] [is] %string%")
            Skript.registerCondition<CondIsNpc>(CondIsNpc::class.java, "%entity% is [a] (npc|citizen)")
            if (plugin.server.pluginManager.isPluginEnabled("Builder")) {
                plugin.logger.info("Getting bacon sandwiches for builders!")
                Skript.registerEffect(EffStartBuilderBuild::class.java,
                        "make citizen %number% build %string% at %location% [speed %number%] for %player%")
                Skript.registerExpression(ExprTopLeftSchematic::class.java, Location::class.java, ExpressionType.SIMPLE,
                        "for builder %number% get the location of the top left of schematic centered " + "at %location%")
                Skript.registerExpression(ExprBottomRightSchematic::class.java, Location::class.java,
                        ExpressionType.SIMPLE,
                        "for builder %number% get the location of the bottom right of schematic centered " + "at %location%")
            }
            if (plugin.server.pluginManager.isPluginEnabled("Sentry")) {
                plugin.logger.info("Roasting bacon for Sentry's!")
                Skript.registerEffect(EffSentryProtect::class.java, "set citizen %number% to protect %player%")
                Skript.registerEffect(EffSentryStopFollow::class.java, "make sentry %number% stop following")
                Skript.registerEffect(EffSentryFollowDistance::class.java,
                        "set follow[ distance] of citizen %number% to %number%")
            }
        } else {
            plugin.logger.info("Citizens not found! Sorry you cant make friends, " + "but don't worry we will still be your friend <3")
        }
    }

}