package net.rayfall.eyesniper2.skRayFall.Holograms;

import java.util.HashMap;
import ch.njol.skript.Skript;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

public class HoloManager {
	
	public HoloManager(){
	}
	
	public static HashMap<String, Hologram> holomap = new HashMap<String, Hologram>();
	
	public static boolean addToHoloMap(String id, Hologram holo){
		if (holomap.containsKey(id)){
			Skript.error("A hologram by the id " + id + " Already exsists!");
			return false;
		}
		else{
			holomap.put(id, holo);
			return true;
		}
	}
	
	public static Hologram removeFromHoloMap(String id){
		if (holomap.containsKey(id)){
			Hologram h = holomap.get(id);
			holomap.remove(id);
			return h;
		}
		else{
			return null;
		}
	}
	public static Hologram getFromHoloMap(String s){
		if (holomap.containsKey(s)){
			return holomap.get(s);
		}
		else{
			return null;
		}
		
	}
	public static void editHoloMap(String s, Hologram h){
		if (holomap.containsKey(s)){
			holomap.remove(s);
			holomap.put(s, h);
		}
		
	}
	public static void dumpHoloMap(){
		for (Hologram h : holomap.values()){
			h.delete();
		}
		holomap.clear();
	}

}
