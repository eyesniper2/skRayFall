package net.rayfall.eyesniper2.skrayfall.generalexpressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

public class ExprNumberOfEnchants extends SimpleExpression<Number> {

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
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    item = (Expression<ItemStack>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected Number[] get(Event evt) {
    if (item != null) {
      return new Number[] {item.getSingle(evt).getEnchantments().size()};
    }
    return null;
  }



}
