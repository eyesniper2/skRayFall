package net.rayfall.eyesniper2.skrayfall.general.events;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.eclipse.jdt.annotation.Nullable;

@Name("On Crafting Click")
@Description({"Called when:",
        "* 1st slot of player crafting inventory is clicked",
        "* 2nd slot of player crafting inventory is clicked",
        "* 3rd slot of player crafting inventory is clicked",
        "* 4th slot of player crafting inventory is clicked",
        "* Result slot of player crafting inventory is clicked",
        "This is called when a player clicks a slot in the players inventory " +
        "crafting slot. 0 = Top left, 1 = Top Right, 2 = Bottom left, 3 = Bottom right, 4 = result slot"})
@Examples({"on crafting click in slot 1:",
        "\tmessage \"Click in slot 1\""})
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
                } else return e.getSlot() == evtslotclick.getSingle().intValue();
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
