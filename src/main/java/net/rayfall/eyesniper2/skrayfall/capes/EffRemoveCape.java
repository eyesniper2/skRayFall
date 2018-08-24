package net.rayfall.eyesniper2.skrayfall.capes;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

import tfw.Capes.General.Capes;

@Name("Remove Cape")
@Description("Removes the cape for the player")
@RequiredPlugins("Capes")
public class EffRemoveCape extends Effect {

    // remove cape of %player%

    private Expression<Player> player;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(Event evt) {
        if (player != null) {
            Player targetPlayer = player.getSingle(evt);
            ItemStack item = targetPlayer.getItemInHand();
            targetPlayer.setItemInHand(null);
            Capes.capeInHand(targetPlayer);
            targetPlayer.setItemInHand(item);
        }
    }
}
