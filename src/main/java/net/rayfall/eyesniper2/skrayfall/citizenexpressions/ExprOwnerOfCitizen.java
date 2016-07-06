package net.rayfall.eyesniper2.skrayfall.citizenexpressions;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.api.trait.trait.Owner;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class ExprOwnerOfCitizen extends SimpleExpression<String> {

  // owner of npc %number%

  Expression<Number> id;

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
    id = (Expression<Number>) exp[0];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Nullable
  protected String[] get(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npc = registry.getById(id.getSingle(evt).intValue());
    if (npc.hasTrait(Owner.class)) {
      return new String[] {npc.getTrait(Owner.class).getOwner()};
    }
    return new String[] {};
  }

}
