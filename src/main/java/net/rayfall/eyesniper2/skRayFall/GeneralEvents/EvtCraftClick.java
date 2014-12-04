package net.rayfall.eyesniper2.skRayFall.GeneralEvents;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.InventorySlot;
import ch.njol.skript.util.Slot;

@SuppressWarnings({ "unused" })
public class EvtCraftClick extends SkriptEvent {
	
	@javax.annotation.Nullable
	private Literal<Number> evtslotclick;
	@SuppressWarnings("unchecked")
	
	@Override
	public boolean init(Literal<?>[] exprs, int arg1, ParseResult arg2) {
		evtslotclick = ((Literal<Number>) exprs[0]);
		return true;
	}
	@Override
	public boolean check(Event evt) {
		final InventoryClickEvent e = (InventoryClickEvent) evt;
		if (InventoryType.CRAFTING == e.getInventory().getType()){
			if (e.getSlotType() != InventoryType.SlotType.QUICKBAR || e.getSlotType() != InventoryType.SlotType.CONTAINER){
				if (evtslotclick == null){
					return true;
				}
				else if (e.getSlot() == evtslotclick.getSingle().intValue()){
					return true;
				}
				else
				return false;
			}
			return false;
		}
		return false;
	}
	@Override
	public String toString(Event e, boolean debug) {
		return "craft click in slot " + evtslotclick.toString(e, debug);
	}
	
}
