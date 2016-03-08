package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffPlayResourcePackSound extends Effect{
	
	// play (resource|[custom ]sound) [sound] pack %string% to %player% [at %-location%] [(and|with) volume %number%] [(and|with) pitch %number%]
	// Suggested and coded by virustotalop here: https://github.com/eyesniper2/skRayFall/pull/7 
	
	private Expression<Player> player;
	private Expression<String> sound;
	private Expression<Number> vol;
	private Expression<Number> pitch;
	private Expression<Location> loc;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		sound = (Expression<String>) e[0];
 		player = (Expression<Player>) e[1];
 		loc =  (Expression<Location>) e[2];
 		vol = (Expression<Number>) e[3];
 		pitch = (Expression<Number>) e[4];
 		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(player != null){
			Location l = player.getSingle(evt).getLocation();
			float fvol = 1;
			float fpitch = 1;
			if(vol != null){
				fvol = vol.getSingle(evt).floatValue();
			}
			if(pitch != null){
				fpitch = pitch.getSingle(evt).floatValue();
			}
			if(loc != null){
				l = loc.getSingle(evt);
			}
			player.getSingle(evt).playSound(l, sound.getSingle(evt).replace("\"", ""),fvol, fpitch);
		}
	}

}
