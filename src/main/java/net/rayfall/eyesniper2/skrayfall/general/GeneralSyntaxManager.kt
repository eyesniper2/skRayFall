package net.rayfall.eyesniper2.skrayfall.general

import ch.njol.skript.Skript
import ch.njol.skript.lang.ExpressionType
import ch.njol.skript.lang.util.SimpleEvent
import ch.njol.skript.registrations.EventValues
import ch.njol.skript.util.Getter
import net.rayfall.eyesniper2.skrayfall.SyntaxManagerInterface
import net.rayfall.eyesniper2.skrayfall.general.effects.*
import net.rayfall.eyesniper2.skrayfall.general.events.EvtCraftClick
import net.rayfall.eyesniper2.skrayfall.general.events.StoreEvent
import net.rayfall.eyesniper2.skrayfall.general.events.StoreListener
import net.rayfall.eyesniper2.skrayfall.general.events.UnstoreEvent
import net.rayfall.eyesniper2.skrayfall.general.expressions.*
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.eclipse.jdt.annotation.Nullable

class GeneralSyntaxManager(val plugin: Plugin) : SyntaxManagerInterface {
    override fun registerSyntax() {
        Skript.registerEffect(EffPlaySoundPacket::class.java,
                "play %string% to %players% [at volume %number%]")
        Skript.registerEvent("Crafting Click", EvtCraftClick::class.java, InventoryClickEvent::class.java,
                "crafting click in slot %number%")
        Skript.registerEvent("On Store", SimpleEvent::class.java, StoreEvent::class.java, "(store|chest add)")
        Skript.registerEvent("On Unstore", SimpleEvent::class.java, UnstoreEvent::class.java,
                "(unstore|chest remove)")
        StoreListener(plugin)
        EventValues.registerEventValue(StoreEvent::class.java, ItemStack::class.java,
                object : Getter<ItemStack, StoreEvent>() {
                    @Nullable
                    override fun get(evt: StoreEvent): ItemStack {
                        return evt.getItem()
                    }
                }, 0)
        EventValues.registerEventValue(StoreEvent::class.java, Player::class.java,
                object : Getter<Player, StoreEvent>() {
                    @Nullable
                    override fun get(evt: StoreEvent): Player {
                        return evt.getPlayer()
                    }
                }, 0)
        EventValues.registerEventValue(StoreEvent::class.java, Inventory::class.java,
                object : Getter<Inventory, StoreEvent>() {
                    @Nullable
                    override fun get(evt: StoreEvent): Inventory {
                        return evt.getInventory()
                    }
                }, 0)
        EventValues.registerEventValue(UnstoreEvent::class.java, ItemStack::class.java,
                object : Getter<ItemStack, UnstoreEvent>() {
                    @Nullable
                    override fun get(evt: UnstoreEvent): ItemStack {
                        return evt.getItem()
                    }
                }, 0)
        EventValues.registerEventValue(UnstoreEvent::class.java, Player::class.java,
                object : Getter<Player, UnstoreEvent>() {
                    @Nullable
                    override fun get(evt: UnstoreEvent): Player {
                        return evt.getPlayer()
                    }
                }, 0)
        EventValues.registerEventValue(UnstoreEvent::class.java, Inventory::class.java,
                object : Getter<Inventory, UnstoreEvent>() {
                    @Nullable
                    override fun get(evt: UnstoreEvent): Inventory {
                        return evt.getInventory()
                    }
                }, 0)
        // Made by njol, ported by eyesniper2 to 1.8. All credit goes to njol on this one!
        Skript.registerEffect(EffMaxHealth::class.java,
                "set rf max[imum] h(p|ealth) of %livingentities% to %number%")

        Skript.registerEffect(EffPlayResourcePackSound::class.java,
                "play (resource|[custom ]sound) [sound] pack %string% to %player% [at %-location%] " + "[(and|with) volume %number%] [(and|with) pitch %number%]")
        Skript.registerEffect(EffFakeFakeLightning::class.java,
                "(create|strike) (fake|ultra|no sound) fake lightning at %location%")
        Skript.registerEffect(EffSetPlayerListName::class.java, "set %player%['s] tab name to %string%")
        Skript.registerEffect(EffSetMetaData::class.java, "set meta %string% for %entity% to %string%")
        Skript.registerExpression(ExprArmorValue::class.java, Number::class.java, ExpressionType.PROPERTY,
                "%player%['s] armo[u]r value")
        Skript.registerExpression(ExprNumberOfEnchants::class.java, Number::class.java, ExpressionType.SIMPLE,
                "number of enchant[ment]s on %itemstack%")
        Skript.registerExpression(ExprTextToLocation::class.java, Location::class.java, ExpressionType.SIMPLE,
                "%string% converted to location")
        Skript.registerExpression(ExprSpecificEnchantIndex::class.java, String::class.java, ExpressionType.SIMPLE,
                "info of enchant[ment] %number% (of|on) %itemstack%")
        Skript.registerExpression(ExprAbsoluteInventoryCount::class.java, Number::class.java, ExpressionType.SIMPLE,
                "(absolute|complex|abs) number of %itemstack% in %player%['s] (inventory|inv)")
        Skript.registerExpression(ExprMetaData::class.java, String::class.java, ExpressionType.SIMPLE,
                "meta %string% for %entity%")
    }

}