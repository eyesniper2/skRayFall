package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

// This was made by w00tmast3r and was simply ported over for 1.8.
// All credit goes to him! https://github.com/w00tmast3r/
@Name("Shiny Item")
@Description({"Show Items by:",
        "* Glow",
        "Makes an item glow as an enchanted item but without the lore"})
@Examples({"give player shiny diamond"})
public class ExprShinyItem extends SimplePropertyExpression<ItemStack, ItemStack> {

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    @Nullable
    public ItemStack convert(ItemStack itemStack) {
        if (itemStack.getType() == Material.BOW) {
            itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
        } else {
            itemStack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
        }
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @Override
    protected String getPropertyName() {
        return "Shiny";
    }
}
