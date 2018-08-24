package net.rayfall.eyesniper2.skrayfall.holograms;


import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.eclipse.jdt.annotation.Nullable;

@Name("Bind Hologram")
@Description({"Bind Hologram by:",
        "* Entity",
        "* Timespan",
        "* Offset x, y & z",
        "Create floating text that follows an entity"})
@RequiredPlugins("Holographic Displays")
public class EffTimedBindedHolo extends Effect {

    private Expression<String> text;
    private Expression<Timespan> time;
    private Expression<Entity> tar;
    private Expression<Number> xcord;
    private Expression<Number> ycord;
    private Expression<Number> zcord;

    // bind hologram %string% to %entity% for %timespan% [offset by %number%, %number%( and|,)
    // %number%]

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        text = (Expression<String>) exp[0];
        tar = (Expression<Entity>) exp[1];
        time = (Expression<Timespan>) exp[2];
        xcord = (Expression<Number>) exp[3];
        ycord = (Expression<Number>) exp[4];
        zcord = (Expression<Number>) exp[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {

        return null;
    }

    @Override
    protected void execute(final Event evt) {
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
        final double fhx = hx;
        final double fhy = hy;
        final double fhz = hz;
        new BukkitRunnable() {
            int ticksRun;

            @Override
            public void run() {
                ticksRun++;
                hologram.teleport(tar.getSingle(evt).getLocation().add(fhx, fhy, fhz));

                if (ticksRun > time.getSingle(evt).getTicks()) {
                    hologram.delete();
                    cancel();
                }
            }
        }.runTaskTimer(Core.plugin, 1L, 1L);
    }
}