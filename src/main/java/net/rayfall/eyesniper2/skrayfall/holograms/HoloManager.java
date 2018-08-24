package net.rayfall.eyesniper2.skrayfall.holograms;

import ch.njol.skript.Skript;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class HoloManager {

    public HoloManager() {
    }

    public static HashMap<String, Hologram> holomap = new HashMap<String, Hologram>();

    /**
     * Store a hologram with an ID in the HoloManager.
     *
     * @param id   The ID text for the hologram.
     * @param holo The hologram object to be stored.
     */
    public static boolean addToHoloMap(String id, Hologram holo) {
        if (holomap.containsKey(id)) {
            Skript.error("A hologram with the id " + id + " already exists!");
            return false;
        } else {
            holomap.put(id, holo);
            return true;
        }
    }

    /**
     * Check if a hologram exists by ID.
     *
     * @param id The ID text for the hologram.
     */
    public static boolean isInHoloMap(String id) {
        if (holomap.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Remove a hologram from the HoloManager by ID. The hologram object will not be destroyed.
     *
     * @param id The ID text for the hologram.
     */
    public static Hologram removeFromHoloMap(String id) {
        if (holomap.containsKey(id)) {
            Hologram holo = holomap.get(id);
            holomap.remove(id);
            return holo;
        } else {
            return null;
        }
    }

    /**
     * Get a hologram from the HoloManager by ID.
     *
     * @param id The ID text for the hologram.
     */
    public static Hologram getFromHoloMap(String id) {
        if (holomap.containsKey(id)) {
            return holomap.get(id);
        } else {
            return null;
        }

    }

    /**
     * Edit a hologram from the HoloManager by ID.
     *
     * @param id   The ID text for the hologram.
     * @param holo The hologram to replace the original stored hologram.
     */
    public static void editHoloMap(String id, Hologram holo) {
        if (holomap.containsKey(id)) {
            holomap.remove(id);
            holomap.put(id, holo);
        }

    }

    /**
     * Dump the current data stored within the HoloManager and remove all holograms in game.
     */
    public static void dumpHoloMap() {
        for (Hologram h : holomap.values()) {
            h.delete();
        }
        holomap.clear();
    }

    /**
     * Make a hologram follow an entity from the HoloManager by ID.
     *
     * @param ent     The entity to be followed.
     * @param id      The ID text for the hologram.
     * @param xoffset The x offset of the hologram from the entity.
     * @param yoffset The y offset of the hologram from the entity.
     * @param zoffset The z offset of the hologram from the entity.
     */
    public static void followEntity(final Entity ent, final String id, final double xoffset,
                                    final double yoffset, final double zoffset) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Hologram hologram = getFromHoloMap(id);
                if (isInHoloMap(id) == true) {
                    hologram.teleport(ent.getLocation().add(xoffset, yoffset, zoffset));
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(Core.plugin, 1L, 1L);
    }
}
