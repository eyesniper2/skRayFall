package net.rayfall.eyesniper2.skrayfall.crackshotevents;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

import com.shampaggon.crackshot.events.WeaponScopeEvent;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EvtUnscope extends SkriptEvent {

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  public boolean check(Event evt) {
    if (evt instanceof WeaponScopeEvent && !((WeaponScopeEvent) evt).isZoomIn()) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean init(Literal<?>[] exp, int arg1, ParseResult arg2) {
    return true;
  }

}
