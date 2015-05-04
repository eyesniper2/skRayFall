package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprNoNBT extends SimplePropertyExpression<ItemStack, ItemStack>{
	//<item stack> no nbt
	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	@Nullable
	public ItemStack convert(ItemStack i) {
		ItemMeta t = i.getItemMeta();
		t.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		t.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
		t.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		t.addItemFlags(ItemFlag.HIDE_DESTROYS);
	    i.setItemMeta(t);
		return i;
 
	}
	@Override
	protected String getPropertyName() {
		return "No NBT";
	}
	

}
