package net.rayfall.eyesniper2.skrayfall.holograms

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class HologramSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {

    override fun registerSyntax() {
        if (plugin.server.pluginManager.isPluginEnabled("HolographicDisplays")) {
            plugin.logger.info("Enabling holograms")
            Skript.registerEffect(EffTimedHologram::class.java,
                    "create hologram %string% at %location% for %timespan%")
            Skript.registerEffect(EffTimedBindedHolo::class.java,
                    "bind hologram %string% to %entity% for %timespan% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffCreateStaticHoloObject::class.java,
                    "create holo object %string% with id %string% at %location%")
            Skript.registerEffect(EffDeleteHoloObject::class.java, "delete holo object %string%")
            Skript.registerEffect(EffCreateInteractiveStaticHolograms::class.java,
                    "create interactive holo object %string% with id %string% at %location%")
            Skript.registerEffect(EffDeleteHoloObject::class.java, "delete holo object %string%")
            Skript.registerEffect(EffEditHoloObject::class.java,
                    "edit holo object %string% to %string% [and set interactivity to %-boolean%]")
            Skript.registerEffect(EffBoundHoloObject::class.java,
                    "create bound holo object %string% with id %string% to %entity% " + "[offset by %number%, %number%( and|,) %number%]")
            Skript.registerEffect(EffEditHoloObjectLine::class.java,
                    "edit holo object %string% [with] line [number] %number% to %string% " + "[and set interactivity to %-boolean%]")
            Skript.registerEffect(EffDeleteHoloObjectLine::class.java,
                    "(delete|remove) line %number% in holo object %string%")
            Skript.registerCondition(CondHologramExists::class.java, "(holo object|hologram) %string% exists")
            Skript.registerEvent("hologram (touch|click)", SimpleEvent::class.java, HoloTouchEvent::class.java,
                    "hologram (touch|click)")
            Skript.registerExpression(ExprGetHoloLine::class.java, String::class.java, ExpressionType.SIMPLE,
                    "text in line %number% of holo[gram] [object] %string%")
            EventValues.registerEventValue(HoloTouchEvent::class.java, String::class.java,
                { evt -> evt.holoId }, 0)
            EventValues.registerEventValue(HoloTouchEvent::class.java, Number::class.java,
                { evt -> evt.lineNumber }, 0)
            EventValues.registerEventValue(HoloTouchEvent::class.java, Player::class.java,
                { evt -> evt.player }, 0)
            Skript.registerEvent("hologram pickup", SimpleEvent::class.java, HoloPickupEvent::class.java,
                    "hologram pickup")
            EventValues.registerEventValue(HoloPickupEvent::class.java, String::class.java,
                { evt -> evt.holoId }, 0)
            EventValues.registerEventValue(HoloPickupEvent::class.java, Number::class.java,
                { evt -> evt.lineNumber }, 0)
            EventValues.registerEventValue(HoloPickupEvent::class.java, Player::class.java,
                { evt -> evt.player }, 0)
            if (plugin.server.pluginManager.isPluginEnabled("ProtocolLib")) {
                plugin.logger.info("Enabling client side holograms")
                Skript.registerEffect(EffCreateInteractiveStaticClientSideHolograms::class.java,
                        "create interactive client side holo object %string% with id %string% at " + "%location% to %player%")
                Skript.registerEffect(EffTimedClientSideHolo::class.java,
                        "display hologram %string% at %location% to %player% for %timespan%")
                Skript.registerEffect(EffCreateStaticClientHoloObject::class.java,
                        "create client side holo object %string% with id %string% at %location% to %player%")
                Skript.registerEffect(EffBoundClientSideHoloObject::class.java,
                        "create client side bound holo object %string% with id %string% to %entity% for " + "%player% [offset by %number%, %number%( and|,) %number%]")
            }
        }
    }

}