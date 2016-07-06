package net.rayfall.eyesniper2.skrayfall.citizenexpressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.npc.NPC;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprLastCitizen extends SimpleExpression<Number> {

  public static NPC lastNPC;

  @Override
  public Class<? extends Number> getReturnType() {
    return Number.class;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @Override
  public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return "Last NPC grabed was ";
  }

  @Override
  @Nullable
  protected Number[] get(final Event event) {
    if (lastNPC != null) {
      return new Number[] {lastNPC.getId()};
    } else {
      Skript.error("You have yet to create a NPC!");
      return null;
    }

  }



}
