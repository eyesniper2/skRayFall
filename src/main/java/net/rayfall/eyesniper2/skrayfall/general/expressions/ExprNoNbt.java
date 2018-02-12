package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.eclipse.jdt.annotation.Nullable;

public class ExprNoNbt extends SimplePropertyExpression<ItemStack, ItemStack> {
  // <item stack> no nbt
  @Override
  public Class<? extends ItemStack> getReturnType() {
    return ItemStack.class;
  }

  @Override
  @Nullable
  public ItemStack convert(ItemStack item) {
    ItemMeta meta = item.getItemMeta();
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
    item.setItemMeta(meta);
    return item;

  }

  @Override
  protected String getPropertyName() {
    return "No NBT";
  }


}
