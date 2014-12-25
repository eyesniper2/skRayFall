package net.rayfall.eyesniper2.skRayFall;


import java.io.IOException;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.rayfall.eyesniper2.skRayFall.CitizenConditions.CondisNPCid;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenAttack;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenLookTarget;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenMove;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenNameVisable;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenSetMaxHealth;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenSetSkin;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenSpeak;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCitizenVulnerablity;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffCreateCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffDeleteCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffDespawnCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffEquipCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSetCitizenName;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSpawnCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprGeneralCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprLastCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprNameOfCitizen;
import net.rayfall.eyesniper2.skRayFall.Commands.EffectLibCommands;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffBasicEffectLib;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffEffectLibAtom;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffEffectLibBleed;
import net.rayfall.eyesniper2.skRayFall.GeneralEvents.EvtCraftClick;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffDeleteScore;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffNameOfScore;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffRemoveScoreboard;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffSetScore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import de.slikey.effectlib.EffectLib;
import de.slikey.effectlib.EffectManager;

public class skRayFall extends JavaPlugin implements Listener {
	
	public static EffectManager effectManager;
	public static Plugin plugin = null;
	
	 @Override
	    public void onEnable() {
		 getLogger().info("Yay! You are running skRayFall 1.0.7!\nNathan and Lewis <3 you");
		 Skript.registerAddon(this);
		 saveDefaultConfig();
		 this.getCommand("skrayfall").setExecutor(new EffectLibCommands(this));
		 if (plugin == null){
			 plugin = this;
		 }
		 getLogger().info("Cooking Bacon...");
		 
		 try {
		        Metrics metrics = new Metrics(this);
		        metrics.start();
		    } catch (IOException e) {
		        getLogger().info("MCStats had an issue :/ " + e.getMessage() + "This will not affect anything. *Server gets moar bacon*");
		    }
		 if (getServer().getPluginManager().isPluginEnabled("Citizens"))
		    {
			 getLogger().info("Getting more bacon for the army of citizens...");
			 Skript.registerEffect(EffCreateCitizen.class,"create [a] citizen named %string% (at|%direction%) %location% [as (a|an) %entitytypes%]");
			 Skript.registerEffect(EffCitizenMove.class,"move citizen %number% to %location% [at speed %number%]");
			 Skript.registerEffect(EffDespawnCitizen.class,"despawn citizen %number%");
			 Skript.registerEffect(EffEquipCitizen.class,"(equip|give) citizen %number% with [an] %itemstack%");
			 Skript.registerEffect(EffSpawnCitizen.class,"respawn citizen %number% (at|%direction%) %location%");
			 Skript.registerEffect(EffDeleteCitizen.class,"delete citizen %number%");
			 //Only supports one living entity add support for groups
			 Skript.registerEffect(EffCitizenSpeak.class,"make citizen %number% (say|communicate) %string% to %livingentities%");
			 Skript.registerEffect(EffSetCitizenName.class,"(set|make) citizen[s] %number% name to %string%");
			 Skript.registerEffect(EffCitizenSetMaxHealth.class,"(set|make) citizen[s] %number% max health [to] %number%");
			 //doesn't work
			 Skript.registerEffect(EffCitizenNameVisable.class,"(set|make) citizen[s] %number% nametag (1¦invisible|0¦visible)");
			 Skript.registerEffect(EffCitizenLookTarget.class,"make citizen %number% look at %location%");
			 Skript.registerEffect(EffCitizenAttack.class,"make citizen %number% (attack|fight) %livingentities%");
			 //buggy
			 Skript.registerEffect(EffCitizenSetSkin.class,"change citizen %number% skin to %string%");
			 Skript.registerEffect(EffCitizenVulnerablity.class,"make citizen %number% (1¦invulnerable|0¦vulnerable)");
			 Skript.registerExpression(ExprLastCitizen.class, Number.class, ExpressionType.SIMPLE, "last created citizen [id]");
			 Skript.registerExpression(ExprNameOfCitizen.class, String.class, ExpressionType.SIMPLE, "name of citizen %number%");
			 Skript.registerExpression(ExprGeneralCitizen.class, Entity.class, ExpressionType.PROPERTY, "citizen %number%");
			 Skript.registerEvent("NPC/Citizen Right Click", SimpleEvent.class, NPCRightClickEvent.class,"(NPC|Citizen) right click");
			 EventValues.registerEventValue(NPCRightClickEvent.class, Player.class, new Getter<Player, NPCRightClickEvent>() {
		            @Override
		            public Player get(NPCRightClickEvent NPCRightClickEvent) {
		                return NPCRightClickEvent.getClicker();
		            }
		        }, 0);
			 EventValues.registerEventValue(NPCRightClickEvent.class, NPC.class, new Getter<NPC, NPCRightClickEvent>() {
		            @Override
		            public NPC get(NPCRightClickEvent NPCRightClickEvent) {
		                return NPCRightClickEvent.getNPC();
		            }
		        }, 0);
			 Skript.registerEvent("NPC/Citizen Left Click", SimpleEvent.class, NPCLeftClickEvent.class,"(NPC|Citizen) left click");
			 EventValues.registerEventValue(NPCLeftClickEvent.class, Player.class, new Getter<Player, NPCLeftClickEvent>() {
		            @Override
		            public Player get(NPCLeftClickEvent NPCLeftClickEvent) {
		                return NPCLeftClickEvent.getClicker();
		            }
		        }, 0);
			 EventValues.registerEventValue(NPCLeftClickEvent.class, NPC.class, new Getter<NPC, NPCLeftClickEvent>() {
		            @Override
		            public NPC get(NPCLeftClickEvent NPCLeftClickEvent) {
		                return NPCLeftClickEvent.getNPC();
		            }
		        }, 0);
			 Skript.registerEvent("NPC/Citizen Death", SimpleEvent.class, NPCDeathEvent.class,"(NPC|Citizen) death");
			 EventValues.registerEventValue(NPCDeathEvent.class, NPC.class, new Getter<NPC, NPCDeathEvent>() {
		            @Override
		            public NPC get(NPCDeathEvent NPCDeathEvent) {
		                return NPCDeathEvent.getNPC();
		            }
		        }, 0);
			 Skript.registerCondition(CondisNPCid.class, "(NPC|Citizen) is %number%");
		    }
		 else{
		      getLogger().info("Citizens not found! Sorry you cant make friends, but don't worry we will still be your friend <3");   
		 }
		 Plugin effectLib = Bukkit.getPluginManager().getPlugin("EffectLib");
		 if (effectLib == null || !(effectLib instanceof EffectLib)) {
			 getLogger().info("No EffectLib Found! *Server gets moar bacon*");
         }
		 else {
			 getLogger().info("Got bacon for the EffectLib partical ninjas!");
			 EffectLib lib = EffectLib.instance();
			 effectManager = new EffectManager(lib);
			 //to be added in the next update
			 //Skript.registerEffect(EffGeneralEffectLib.class, "(spawn|create|apply) effectlib effect %string% (at|on|to) [the] %entities/location% for %timespan%");
			 Skript.registerEffect(EffEffectLibAtom.class, "(spawn|create|apply) the atom (formation|effect) (at|on|to) [the] %entities/location% for %timespan%");
			 Skript.registerEffect(EffEffectLibBleed.class, "(spawn|create|apply) the bleed (formation|effect) (at|on|to) [the] %entities/location% for %timespan%");
			 Skript.registerEffect(EffBasicEffectLib.class,"spawn formation %string% at %location% for %timespan%");
			 
		 }
		 
		 Skript.registerEvent("Crafting Click", EvtCraftClick.class, InventoryClickEvent.class,"crafting click in slot %number%");
		 Skript.registerEffect(EffNameOfScore.class,"set name of sidebar of %player% to %string%");
		 Skript.registerEffect(EffSetScore.class,"set score %string% in sidebar of %player% to %number%");
		 Skript.registerEffect(EffDeleteScore.class,"delete score %string% in sidebar of %player%");
		 Skript.registerEffect(EffRemoveScoreboard.class,"(wipe|erase) %player%['s] sidebar");
		 getLogger().info("Bacon is ready!");
	 }
	 
	 @Override
	    public void onDisable(){
		 getLogger().info("Awww, you have disabled skRayFall D:");
	 }
	 
	 
	 }

