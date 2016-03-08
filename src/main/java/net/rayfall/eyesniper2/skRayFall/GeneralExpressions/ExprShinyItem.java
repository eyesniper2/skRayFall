package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

// This was made by w00tmast3r and was simply ported over for 1.8. 
// All credit goes to him! https://github.com/w00tmast3r/
public class ExprShinyItem extends SimplePropertyExpression<ItemStack, ItemStack>{

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	@Nullable
	public ItemStack convert(ItemStack itemStack) {
	        if (itemStack.getType() == Material.BOW) itemStack.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
	        else itemStack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 1);
	        ItemMeta t = itemStack.getItemMeta();
			t.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			itemStack.setItemMeta(t);
	        return itemStack;
	    }

	@Override
	protected String getPropertyName() {
		return "Shiny";
	}

}
