package net.rayfall.eyesniper2.skrayfall.crackshot.events;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

import com.shampaggon.crackshot.events.WeaponScopeEvent;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("On CrackShot Scope/Unscope")
@Description("An event for when player scopes or unscopes out of a weapon. " +
        "Event-string will return name of the player’s weapon.")
@RequiredPlugins("CrackShot")
@DocumentationId("EvtCrackShotScopeUnscope")
public class EvtScope extends SkriptEvent {

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    public boolean check(Event evt) {
        if (evt instanceof WeaponScopeEvent && ((WeaponScopeEvent) evt).isZoomIn()) {
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
