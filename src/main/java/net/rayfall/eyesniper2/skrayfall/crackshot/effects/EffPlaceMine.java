package net.rayfall.eyesniper2.skrayfall.crackshot.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import com.shampaggon.crackshot.CSUtility;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffPlaceMine extends Effect {

  // (place|set|spawn) mine at %location% for %player% as %string%

  private Expression<Location> loc;
  private Expression<Player> player;
  private Expression<String> weaponName;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    loc = (Expression<Location>) exp[0];
    player = (Expression<Player>) exp[1];
    weaponName = (Expression<String>) exp[2];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    CSUtility cs = new CSUtility();
    cs.spawnMine(player.getSingle(evt), loc.getSingle(evt), weaponName.getSingle(evt));
  }
}
