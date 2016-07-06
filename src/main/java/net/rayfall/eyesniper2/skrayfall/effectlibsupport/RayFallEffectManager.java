package net.rayfall.eyesniper2.skrayfall.effectlibsupport;

import de.slikey.effectlib.Effect;

import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class RayFallEffectManager {

  private HashMap<String, Effect> effectMap = new HashMap<String, Effect>();
  @SuppressWarnings("unused")
  private Plugin plugin;

  public RayFallEffectManager(Plugin plugin) {
    this.plugin = plugin;
  }
  
  /**
   * Dump the current data stored within the RayFallEffectManager and stop all effects.
   */
  public void dumpData() {
    for (Effect e : effectMap.values()) {
      e.cancel();
    }
  }

  /**
   * Store an active effect within the RayFallEffectManager.
   * 
   * @param effect The EffectLib effect to store
   * @param id The ID text for the effect.
   */
  public boolean setEffect(Effect effect, String id) {
    if (effectMap.containsKey(id)) {
      return false;
    } else {
      effectMap.put(id, effect);
      return true;
    }
  }

  /**
   * Remove and stop a stored active effect within the RayFallEffectManager.
   * 
   * @param id The ID text for the effect.
   */
  public void deleteEffect(String id) {
    if (effectMap.containsKey(id)) {
      Effect effect = effectMap.get(id);
      effect.cancel();
      effectMap.remove(id);
    }
  }
}
