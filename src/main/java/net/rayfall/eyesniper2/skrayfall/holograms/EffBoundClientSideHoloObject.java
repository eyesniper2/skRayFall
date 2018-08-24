package net.rayfall.eyesniper2.skrayfall.holograms;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.eclipse.jdt.annotation.Nullable;

@Name("Create Static Object Based Holograms")
@Description({"Create non moving object based holograms! Use the interactive versions " +
        "if you want to take advantage of the touch and pickup events!",
        "",
        "Client side holograms are also possible with the second syntax but will also require protocollib."})
@RequiredPlugins({"Holographic Displays", "Protocollib"})
@DocumentationId("EffCreateStaticObjectBasedHolograms")
public class EffBoundClientSideHoloObject extends Effect {

    // create client side bound holo object %string% with id %string% to %entity% for %player% [offset
    // by %number%, %number%( and|,) %number%]

    private Expression<String> text;
    private Expression<String> id;
    private Expression<Entity> tar;
    private Expression<Player> player;
    private Expression<Number> xcord;
    private Expression<Number> ycord;
    private Expression<Number> zcord;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        text = (Expression<String>) exp[0];
        id = (Expression<String>) exp[1];
        tar = (Expression<Entity>) exp[2];
        player = (Expression<Player>) exp[3];
        xcord = (Expression<Number>) exp[4];
        ycord = (Expression<Number>) exp[5];
        zcord = (Expression<Number>) exp[6];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        double hx = 0;
        double hy = 0;
        double hz = 0;
        if (xcord != null) {
            hx = xcord.getSingle(evt).doubleValue();
        }
        if (ycord != null) {
            hy = ycord.getSingle(evt).doubleValue();
        }
        if (zcord != null) {
            hz = zcord.getSingle(evt).doubleValue();
        }
        final Hologram hologram =
                HologramsAPI.createHologram(Core.plugin, tar.getSingle(evt).getLocation());
        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.showTo(player.getSingle(evt));
        visibilityManager.setVisibleByDefault(false);
        String core = text.getSingle(evt).replace("\"", "");
        while (core.indexOf(";") != -1) {
            String line = core.substring(0, core.indexOf(";"));
            core = core.substring(core.indexOf(";") + 1);
            if (line.startsWith("ItemStack:")) {
                line = line.substring(line.indexOf(":") + 1);
                int meta = 0;
                if (line.contains(":")) {
                    try {
                        meta = Integer.parseInt(line.substring(line.indexOf(":") + 1));
                    } catch (NumberFormatException exception) {
                        Skript.error("Meta data could not be parsed correctly!");
                        continue;
                    }

                    line = line.substring(0, line.indexOf(":"));
                }
                ItemStack stack = new ItemStack(Material.AIR, 1);
                try {
                    Material mat = Material.valueOf(line.toUpperCase().replace(" ", "_"));
                    stack = new ItemStack(mat, 1);
                    if (meta != 0) {
                        stack = new ItemStack(mat, 1, (byte) meta);
                    }
                } catch (IllegalArgumentException exception) {
                    Skript.error("A item under that name does not exsist!");
                    continue;
                }

                hologram.appendItemLine(stack);
            } else {
                hologram.appendTextLine(line);
            }
        }
        hologram.appendTextLine(core);
        if (HoloManager.addToHoloMap(id.getSingle(evt).replace("\"", ""), hologram) == false) {
            hologram.delete();
        } else {
            final String tid = id.getSingle(evt).replace("\"", "");
            HoloManager.followEntity(tar.getSingle(evt), tid, hx, hy, hz);
        }

    }

}
