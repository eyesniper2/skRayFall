package net.rayfall.eyesniper2.skRayFall.GeneralExpressions;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprAbsoluteInventoryCount extends SimpleExpression<Number>{
	
	//(absolute|complex|abs) number of %itemstack% in %player%['s] (inventory|inv)
	
	private Expression<Player> player;
	private Expression<ItemStack> item;

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
		player = (Expression<Player>) e[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	@Nullable
	protected Number[] get(Event evt) {
		if(player != null && item != null && player.getSingle(evt).getInventory() != null && player.getSingle(evt).getInventory().contains(item.getSingle(evt))){
			ItemStack base = item.getSingle(evt);
			int count = 0;
			for(Map.Entry<Integer, ? extends ItemStack> i : player.getSingle(evt).getInventory().all(base.getType()).entrySet()) {
				if(i.getValue().isSimilar(base)){
					count += i.getValue().getAmount();
				}
			}
			return new Number[] {count};
		}
		return new Number[] {0};
	}

}
