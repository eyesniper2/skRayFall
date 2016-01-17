package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprNumberOfEnchants extends SimpleExpression<Number>{
	
	// number of enchant[ment]s on %itemstack%
	
	Expression<ItemStack> item;

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		item = (Expression<ItemStack>) e[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Number[] get(Event evt) {
		if(item != null){
			return new Number[] {item.getSingle(evt).getEnchantments().size()};
		}
		return null;
	}
	
	
	
	


}
