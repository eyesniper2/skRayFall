package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import org.bukkit.inventory.ItemStack;
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

		return i;
		
 
	}
	@Override
	protected String getPropertyName() {
		return "No NBT";
	}
	

}
