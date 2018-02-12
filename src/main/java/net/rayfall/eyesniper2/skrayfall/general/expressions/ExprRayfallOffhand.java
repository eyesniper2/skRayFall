package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

public class ExprRayfallOffhand extends SimpleExpression<ItemStack> {
  // %player%['s] offhand
  // item in %player%['s] offhand
  
  private Expression<Player> player;

  @Override
  public Class<ItemStack> getReturnType() {
    return ItemStack.class;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
    player = (Expression<Player>) expr[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected ItemStack[] get(Event evt) {
    if (player != null) {
      return new ItemStack[] {player.getSingle(evt).getInventory().getItemInOffHand()};
    }
    return null;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  @Nullable
  public Class<?>[] acceptChange(final ChangeMode mode) {
    switch (mode) {
      case SET:
        return CollectionUtils.array(ItemStack.class);
      case ADD:
      case REMOVE:
      case DELETE:
      case REMOVE_ALL:
      case RESET:
      default:
        return null;
    }
  }
  
  @Override
  public void change(Event evt, @Nullable Object[] delta, ChangeMode mode) {
    if (player != null) {
      switch (mode) {
        case SET:
          assert delta != null;
          ItemStack newItem = (ItemStack) delta[0];
          player.getSingle(evt).getInventory().setItemInOffHand(newItem);
          break;
        case REMOVE:
        case DELETE:
        case REMOVE_ALL:
          player.getSingle(evt).getInventory().setItemInOffHand(null);
          break;
        default:
          assert false;
      }
    }
  }
}
