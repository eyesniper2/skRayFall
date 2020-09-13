package net.rayfall.eyesniper2.skrayfall.general.effects;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Play Resource Pack Sound")
@Description("Play a sound from a resource pack.")
public class EffPlayResourcePackSound extends Effect {

    // play (resource|[custom ]sound) [sound] pack %string% to %player% [at %-location%] [(and|with)
    // volume %number%] [(and|with) pitch %number%]
    // Suggested and coded by virustotalop here: https://github.com/eyesniper2/skRayFall/pull/7

    private Expression<Player> player;
    private Expression<String> sound;
    private Expression<Number> vol;
    private Expression<Number> pitch;
    private Expression<Location> loc;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        sound = (Expression<String>) exp[0];
        player = (Expression<Player>) exp[1];
        loc = (Expression<Location>) exp[2];
        vol = (Expression<Number>) exp[3];
        pitch = (Expression<Number>) exp[4];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (player != null) {
            Location locationOfSound = player.getSingle(evt).getLocation();
            float fVol = 1.0F;
            float fPitch = 1.0F;
            if (vol != null) {
                fVol = vol.getSingle(evt).floatValue();
            }
            if (pitch != null) {
                fPitch = pitch.getSingle(evt).floatValue();
            }
            if (loc != null) {
                locationOfSound = loc.getSingle(evt);
            }
            player.getSingle(evt).playSound(locationOfSound, sound.getSingle(evt).replace("\"", ""),
                    fVol, fPitch);
        }
    }
}
