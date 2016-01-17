package net.rayfall.eyesniper2.skRayFall.Holograms;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.handler.PickupHandler;
import com.gmail.filoghost.holographicdisplays.api.handler.TouchHandler;
import com.gmail.filoghost.holographicdisplays.api.line.ItemLine;
import com.gmail.filoghost.holographicdisplays.api.line.TextLine;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffEditHoloObjectLine extends Effect {

	private Expression<String> text;
	private Expression<String> id;
	private Expression<Boolean> interactive;
	private Expression<Number> lineNumber;

	// edit holo object %string% line %number% to %string% [and set
	// interactivity to %boolean%];

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] e, int arg1, Kleenean arg2,
			ParseResult arg3) {
		id = (Expression<String>) e[0];
		lineNumber = (Expression<Number>) e[1];
		text = (Expression<String>) e[2];
		interactive = (Expression<Boolean>) e[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return null;
	}

	@Override
	protected void execute(final Event evt) {
		if (HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", "")) != null) {
			Hologram hologram = HoloManager.getFromHoloMap(id.getSingle(evt)
					.replace("\"", ""));
			if(lineNumber.getSingle(evt).intValue() <= 0){
				Skript.error("Please use a line number greater than 0");
				return;
			}
			final int finalLineNumber = lineNumber.getSingle(evt).intValue() - 1;
			boolean check = false;
			if (this.interactive != null && interactive.getSingle(evt) != null) {
				check = interactive.getSingle(evt).booleanValue();
			}
			hologram.getLine(finalLineNumber).removeLine();
			String line = text.getSingle(evt).replace("\"", "")
					.replace(";", "");
			if (line.startsWith("ItemStack:")) {
				line = line.substring(line.indexOf(":") + 1);
				int meta = 0;
				if (line.contains(":")) {
					try {
						meta = Integer.parseInt(line.substring(line
								.indexOf(":") + 1));
					} catch (NumberFormatException e) {
						Skript.error("Meta data could not be parsed correctly!");
						return;
					}

					line = line.substring(0, line.indexOf(":"));
				}
				ItemStack stack = new ItemStack(Material.AIR, 1);
				try {
					Material mat = Material.valueOf(line.toUpperCase().replace(
							" ", "_"));
					stack = new ItemStack(mat, 1);
					if (meta != 0) {
						stack = new ItemStack(mat, 1, (byte) meta);
					}
				} catch (IllegalArgumentException e) {
					Skript.error("A item under that name does not exsist!");
					return;
				}

				ItemLine itemline = hologram.insertItemLine(finalLineNumber, stack);
				if (check == true) {
					itemline.setTouchHandler(new TouchHandler() {
						@Override
						public void onTouch(Player player) {
							HoloTouchEvent event = new HoloTouchEvent(player,
									id.getSingle(evt).replace("\"", ""),
									finalLineNumber);
							Bukkit.getPluginManager().callEvent(event);
						}
					});
					itemline.setPickupHandler(new PickupHandler() {
						@Override
						public void onPickup(Player player) {
							HoloPickupEvent event = new HoloPickupEvent(player,
									id.getSingle(evt).replace("\"", ""),
									finalLineNumber);
							Bukkit.getPluginManager().callEvent(event);
						}
					});
				}
			} else {
				TextLine textline = hologram.insertTextLine(finalLineNumber, line);
				if (check == true) {
					textline.setTouchHandler(new TouchHandler() {
						public void onTouch(Player player) {
							HoloTouchEvent event = new HoloTouchEvent(player,
									id.getSingle(evt).replace("\"", ""),
									finalLineNumber);
							Bukkit.getPluginManager().callEvent(event);
						}
					});
				}
			}
			HoloManager.editHoloMap(id.getSingle(evt).replace("\"", ""),
					hologram);
		} else {
			Skript.error("That hologram does not exist!");
		}
	}

}
