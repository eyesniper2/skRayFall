package net.rayfall.eyesniper2.skrayfall.general.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Play Sound")
@Description({"Play sound by:",
        "* Player",
        "* Client",
        "* Volume",
        "* This will play a sound to a player and nobody else",
        "",
        "If you want a list of skRayFall sounds go https://hub.spigotmc.org/javadocs/spigot/ click ctrl + f and type" +
                " 'sound' then press the down arrow once and then click the sound that is shown in green text and that " +
                "will list all the sounds for you"})
public class EffPlaySoundPacket extends Effect {

    // play %string% to %player%

    private Expression<Player> player;
    private Expression<String> sound;
    private Expression<Number> vol;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        try {
            Sound.valueOf(exp[0].toString().replace("\"", "").trim().replace(" ", "_").toUpperCase());
            sound = (Expression<String>) exp[0];
        } catch (IllegalArgumentException | NullPointerException error) {
            Skript.error(exp[0].toString().replace("\"", "") + " is not a valid sound type");
            return false;
        }
        player = (Expression<Player>) exp[1];
        vol = (Expression<Number>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Sound soundToPlay = Sound
                .valueOf(sound.getSingle(evt).replace("\"", "").trim().replace(" ", "_").toUpperCase());
        if (vol != null) {
            for (Player p : player.getAll(evt)) {
                p.playSound(p.getLocation(), soundToPlay, (float) vol.getSingle(evt).doubleValue(),
                        (float) 1.0D);
            }
        } else {
            for (Player p : player.getAll(evt)) {
                p.playSound(p.getLocation(), soundToPlay, (float) 1.0D, (float) 1.0D);
            }
        }
    }
}
