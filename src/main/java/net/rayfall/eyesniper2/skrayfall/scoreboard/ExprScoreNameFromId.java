package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprScoreNameFromId extends SimpleExpression<String> {

  // score (name|title) (of|from) id %string%

  Expression<String> id;


  @Override
  public Class<? extends String> getReturnType() {
    return String.class;
  }

  @Override
  public boolean isSingle() {
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<String>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  @Nullable
  protected String[] get(Event evt) {
    return new String[] {Core.sbManager.getNameOfSingleScore(id.getSingle(evt).replace("\"", ""))};
  }

}
