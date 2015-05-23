package net.rayfall.eyesniper2.skRayFall.GeneralEffects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffNewParticles extends Effect{
	@SuppressWarnings("unused")
	private Expression<Number> partNum;
	private Expression<Player> player;
	private Expression<Location> location;
	private Expression<String> type;
	
	//show new %number% of %string% particle[s] at %location% for %player%

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		partNum = (Expression<Number>) e[0];
		type = (Expression<String>) e[1];
		location = (Expression<Location>) e[2];
		player = (Expression<Player>) e[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event evt) {
		int id = 0;
		String core = type.getSingle(evt);
		if (core.toUpperCase().replace(" ", "_").contains("BLOCK_CRACK")||core.toUpperCase().replace(" ", "_").contains("BLOCK_DUST")){
			int index = type.getSingle(evt).lastIndexOf("_");
			try{
			id = Integer.parseInt(type.getSingle(evt).substring(index + 1));
			}
			catch(Exception e){
				Skript.error("Could not parse datavalue!");
				id = 0;
			}
			core = core.substring(0, index);
		}
		player.getSingle(evt).playEffect(location.getSingle(evt), org.bukkit.Effect.getByName(core), Material.getMaterial(id));
		
	}

}
