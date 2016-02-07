package net.rayfall.eyesniper2.skRayFall.CrackShotEvents;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import com.shampaggon.crackshot.events.WeaponScopeEvent;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtScope extends SkriptEvent{

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	public boolean check(Event evt) {
		if(evt instanceof WeaponScopeEvent && ((WeaponScopeEvent) evt).isZoomIn()){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean init(Literal<?>[] e, int arg1, ParseResult arg2) {
		return true;
	}

}
