package net.rayfall.eyesniper2.skRayFall.EffectLibSupport;

import java.util.HashMap;

import org.bukkit.plugin.Plugin;

import de.slikey.effectlib.Effect;

public class skRayFallEffectManager {
	
	private HashMap<String, Effect> effectMap = new HashMap<String, Effect>();
	@SuppressWarnings("unused")
	private Plugin plugin; 
	
	public skRayFallEffectManager(Plugin p){
		this.plugin = p;
	}
	
	public void dumpData(){
		for(Effect e: effectMap.values()){
			e.cancel();
		}
	}
	
	public boolean setEffect(Effect e, String s){
		if (effectMap.containsKey(s)){
			return false;
		}
		else{
			effectMap.put(s, e);
			return true;
		}
	}
	
	public void deleteEffect(String s){
		if(effectMap.containsKey(s)){
			Effect e = effectMap.get(s);
			e.cancel();
			effectMap.remove(s);
		}
	}
	

}
