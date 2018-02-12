package net.rayfall.eyesniper2.skrayfall.general.events;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.eclipse.jdt.annotation.Nullable;

public class EvtCraftClick extends SkriptEvent {

  @Nullable
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
    if (InventoryType.CRAFTING == e.getInventory().getType()) {
      if (e.getSlotType() != InventoryType.SlotType.QUICKBAR
          && e.getSlotType() != InventoryType.SlotType.CONTAINER) {
        if (evtslotclick == null) {
          return true;
        } else if (e.getSlot() == evtslotclick.getSingle().intValue()) {
          return true;
        } else {
          return false;
        }
      }
      return false;
    }
    return false;
  }

  @Override
  public String toString(Event event, boolean debug) {
    return "craft click in slot " + evtslotclick.toString(event, debug);
  }

}
