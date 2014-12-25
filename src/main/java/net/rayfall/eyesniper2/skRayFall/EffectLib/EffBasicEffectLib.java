package net.rayfall.eyesniper2.skRayFall.EffectLib;

import net.rayfall.eyesniper2.skRayFall.skRayFall;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;
import de.slikey.effectlib.effect.AtomEffect;
import de.slikey.effectlib.effect.BigBangEffect;
import de.slikey.effectlib.effect.BleedEffect;
import de.slikey.effectlib.effect.DragonEffect;
import de.slikey.effectlib.effect.EarthEffect;
import de.slikey.effectlib.effect.ExplodeEffect;
import de.slikey.effectlib.effect.HelixEffect;
import de.slikey.effectlib.effect.ShieldEffect;

public class EffBasicEffectLib extends Effect{
	
	private Expression<Timespan> dur;
	private Expression<String> type;
	private Expression<Location> target;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int pat, Kleenean arg2,
			ParseResult arg3) {
		type = (Expression<String>) e[0];
		target = (Expression<Location>) e[1];
		dur = (Expression<Timespan>) e[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event evt, boolean arg1) {
		return null;
	}
	@Override
	protected void execute(Event evt) {
		String hold = type.getSingle(evt).toString();
		switch(hold.replace("\"", "").toLowerCase().trim()){
		case "atom":
			AtomEffect atom = new AtomEffect(skRayFall.effectManager);
			atom.setLocation(target.getSingle(evt));
			atom.iterations = dur.getSingle(evt).getTicks();
			atom.start();
			return;
		case "bigbang": case "big bang":
			BigBangEffect bigbang = new BigBangEffect(skRayFall.effectManager);
			bigbang.setLocation(target.getSingle(evt));
			bigbang.iterations = dur.getSingle(evt).getTicks();
			bigbang.start();
			return;
		case "shield":
			ShieldEffect sh = new ShieldEffect(skRayFall.effectManager);
			sh.setLocation(target.getSingle(evt));
			sh.iterations = dur.getSingle(evt).getTicks();
			sh.start();
			return;
		case "bleed": case "bleeding":
			BleedEffect be = new BleedEffect(skRayFall.effectManager);
			be.setLocation(target.getSingle(evt));
			be.iterations = dur.getSingle(evt).getTicks();
			be.start();
			return;
		case "dragon":
			DragonEffect de = new DragonEffect(skRayFall.effectManager);
			de.setLocation(target.getSingle(evt));
			de.iterations = dur.getSingle(evt).getTicks();
			de.start();
			return;
		case "earth":
			EarthEffect ee = new EarthEffect(skRayFall.effectManager);
			ee.setLocation(target.getSingle(evt));
			ee.iterations = dur.getSingle(evt).getTicks();
			ee.start();
			return;
		case "explode":
			ExplodeEffect exE = new ExplodeEffect(skRayFall.effectManager);
			exE.setLocation(target.getSingle(evt));
			exE.iterations = dur.getSingle(evt).getTicks();
			exE.start();
			return;
		case "helix":
			HelixEffect hx = new HelixEffect(skRayFall.effectManager);
			hx.setLocation(target.getSingle(evt));
			hx.iterations = dur.getSingle(evt).getTicks();
			hx.start();
			return;
		default:
			Skript.error("Oh no! The String you entered was not a vaild effect. Halted Effect! Don't worry eyesniper2 is working on better error management within skript. This is just a dev build for you guys to play with early <3");
		}

		
	}

}
