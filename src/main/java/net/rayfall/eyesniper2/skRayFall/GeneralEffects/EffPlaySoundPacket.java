package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffPlaySoundPacket extends Effect{
	
	//play %string% to %player%
	
	private Expression<Player> player;
	private Expression<String> sound;
	private Expression<Number> vol;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2, ParseResult arg3) {
		sound = (Expression<String>) e[0];
		player = (Expression<Player>) e[1];
		vol = (Expression<Number>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(Event evt) {
		if(Sound.valueOf(e[0].toString().replace("\"", "")) != null) {
			if (vol != null){
				player.getSingle(evt).playSound(player.getSingle(evt).getLocation(),			
						sound.getSingle(evt).replace("\"", "").toUpperCase(), (float) vol.getSingle(evt).doubleValue(), (float) 1.0D);
			}
			else{
				player.getSingle(evt).playSound(player.getSingle(evt).getLocation(),			
						sound.getSingle(evt).replace("\"", "").toUpperCase(), (float) 1.0D, (float) 1.0D);
			}
		}
		else {
			if (vol != null){
				player.getSingle(evt).playSound(player.getSingle(evt).getLocation(),			
						sound.getSingle(evt), (float) vol.getSingle(evt).doubleValue(), (float) 1.0D);
			}
			else{
				player.getSingle(evt).playSound(player.getSingle(evt).getLocation(),			
						sound.getSingle(evt), (float) 1.0D, (float) 1.0D);
			}
		}
	}
}
