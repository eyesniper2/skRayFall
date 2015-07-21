package net.rayfall.eyesniper2.skRayFall;


import java.io.IOException;

import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import net.rayfall.eyesniper2.skRayFall.CitizenConditions.CondisNPCNamed;
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
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffGiveLookCloseTrait;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSentryFollowDistance;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSentryProtect;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSentryStopFollow;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSetCitizenName;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffSpawnCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenEffects.EffStartBuilderBuild;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprBottomRightSchematic;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprGeneralCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprLastCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprNameOfCitizen;
import net.rayfall.eyesniper2.skRayFall.CitizenExpressions.ExprTopLeftSchematic;
import net.rayfall.eyesniper2.skRayFall.Commands.GeneralCommands;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffBasicEffectLib;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffEffectLibAtom;
import net.rayfall.eyesniper2.skRayFall.EffectLib.EffEffectLibBleed;
import net.rayfall.eyesniper2.skRayFall.GeneralEffects.EffMaxHealth;
import net.rayfall.eyesniper2.skRayFall.GeneralEffects.EffPlaySoundPacket;
import net.rayfall.eyesniper2.skRayFall.GeneralEffects.EffSetPlayerListName;
import net.rayfall.eyesniper2.skRayFall.GeneralEvents.EvtCraftClick;
import net.rayfall.eyesniper2.skRayFall.GeneralEvents.StoreEvent;
import net.rayfall.eyesniper2.skRayFall.GeneralEvents.StoreListener;
import net.rayfall.eyesniper2.skRayFall.GeneralEvents.UnstoreEvent;
import net.rayfall.eyesniper2.skRayFall.GeneralExpressions.ExprNoNBT;
import net.rayfall.eyesniper2.skRayFall.GeneralExpressions.ExprShinyItem;
import net.rayfall.eyesniper2.skRayFall.Holograms.EditHoloObject;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffBoundHoloObject;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffCreateStaticClientHoloObject;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffCreateStaticHoloObject;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffDeleteHoloObject;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffTimedBindedHolo;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffTimedClientSideHolo;
import net.rayfall.eyesniper2.skRayFall.Holograms.EffTimedHologram;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.CondisScoreboardSet;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffDeleteScore;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffNameOfScore;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffRemoveScoreBelowName;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffRemoveScoreTab;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffRemoveScoreboard;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffSetScore;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffSetScoreBelowName;
import net.rayfall.eyesniper2.skRayFall.Scoreboard.EffSetScoreTab;



import net.rayfall.eyesniper2.skRayFall.V1_8.EffActionBarV1_8;
import net.rayfall.eyesniper2.skRayFall.V1_8.EffParticlesV1_8;
import net.rayfall.eyesniper2.skRayFall.V1_8.EffTabTitlesV1_8;
import net.rayfall.eyesniper2.skRayFall.V1_8.EffTitleV1_8;
import net.rayfall.eyesniper2.skRayFall.V1_8_3.EffActionBarV1_8_3;
import net.rayfall.eyesniper2.skRayFall.V1_8_3.EffParticlesV1_8_3;
import net.rayfall.eyesniper2.skRayFall.V1_8_3.EffTabTitlesV1_8_3;
import net.rayfall.eyesniper2.skRayFall.V1_8_3.EffTitleV1_8_3;
import net.rayfall.eyesniper2.skRayFall.V1_8_4.EffActionBarV1_8_4;
import net.rayfall.eyesniper2.skRayFall.V1_8_4.EffParticlesV1_8_4;
import net.rayfall.eyesniper2.skRayFall.V1_8_4.EffTabTitlesV1_8_4;
import net.rayfall.eyesniper2.skRayFall.V1_8_4.EffTitleV1_8_4;
import net.rayfall.eyesniper2.skRayFall.Voting.RayFallVoteEvent;
import net.rayfall.eyesniper2.skRayFall.Voting.RayFallVoteListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.eclipse.jdt.annotation.Nullable;
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
	public boolean enableFastScoreboards = true;
	
	 @Override
	    public void onEnable() {
		 getLogger().info("Yay! You are running skRayFall " + this.getDescription().getVersion() + "!\nNathan and Lewis <3 you");
		 getServer().getPluginManager().registerEvents(this, this);
		 Skript.registerAddon(this);
		 saveDefaultConfig();
		 this.getCommand("skrayfall").setExecutor(new GeneralCommands(this));
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
			 Skript.registerEffect(EffDeleteCitizen.class,"(remove|destroy) citizen %number%");
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
			 Skript.registerEffect(EffGiveLookCloseTrait.class, "give npc %number% the look close trait");
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
			 EventValues.registerEventValue(NPCRightClickEvent.class, Number.class, new Getter<Number, NPCRightClickEvent>() {
		            @Override
		            public Number get(NPCRightClickEvent NPCRightClickEvent) {
		                return NPCRightClickEvent.getNPC().getId();
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
			 EventValues.registerEventValue(NPCLeftClickEvent.class, Number.class, new Getter<Number, NPCLeftClickEvent>() {
		            @Override
		            public Number get(NPCLeftClickEvent NPCLeftClickEvent) {
		                return NPCLeftClickEvent.getNPC().getId();
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
			 Skript.registerCondition(CondisNPCNamed.class, "(NPC|Citizen)['s] [is] name[d] [is] %string%");
			 if(getServer().getPluginManager().isPluginEnabled("Builder")){
				 getLogger().info("Getting bacon sandwiches for builders!");
				 Skript.registerEffect(EffStartBuilderBuild.class, "make citizen %number% build %string% at %location% [speed %number%] for %player%");
				 Skript.registerExpression(ExprTopLeftSchematic.class, Location.class, ExpressionType.SIMPLE, "for builder %number% get the location of the top left of schematic centered at %location%");
				 Skript.registerExpression(ExprBottomRightSchematic.class, Location.class, ExpressionType.SIMPLE, "for builder %number% get the location of the bottom right of schematic centered at %location%");
			 }
			 if(getServer().getPluginManager().isPluginEnabled("Sentry")){
				 getLogger().info("Roasting bacon for Sentry's!");
				 Skript.registerEffect(EffSentryProtect.class, "set citizen %number% to protect %player%");
				 Skript.registerEffect(EffSentryStopFollow.class, "make sentry %number% stop following");
				 Skript.registerEffect(EffSentryFollowDistance.class, "set follow[ distance] of citizen %number% to %number%");
			 }
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
			 //more stuff to be added
			 Skript.registerEffect(EffEffectLibAtom.class, "(spawn|create|apply) the atom (formation|effect) (at|on|to) [the] %entities/location% for %timespan%");
			 Skript.registerEffect(EffEffectLibBleed.class, "(spawn|create|apply) the bleed (formation|effect) (at|on|to) [the] %entities/location% for %timespan%");
			 Skript.registerEffect(EffBasicEffectLib.class,"spawn formation %string% at %location% for %timespan%");
		 }
		//Votifier Stuff 
		 if (getServer().getPluginManager().isPluginEnabled("Votifier")){
			 getLogger().info("Getting more bacon for the Votifier runners!");
			 new RayFallVoteListener(this);
			 Skript.registerEvent("On Vote", SimpleEvent.class, RayFallVoteEvent.class, "vote[ing]");
			 EventValues.registerEventValue(RayFallVoteEvent.class, Player.class, new Getter<Player, RayFallVoteEvent>() {
				    @Nullable
		            @Override
		            public Player get(RayFallVoteEvent evt) {
		            	return evt.getPlayer();
		            }
		        }, 0);
			 EventValues.registerEventValue(RayFallVoteEvent.class, String.class, new Getter<String, RayFallVoteEvent>() {
				    @Nullable
		            @Override
		            public String get(RayFallVoteEvent evt) {
		            	return evt.getSite();
		            }
		        }, 0);
		 }
		 else{
			 getLogger().info("No Votifier Found! *Checks oven for finished bacon*");
		 }
		 if (getServer().getPluginManager().isPluginEnabled("HolographicDisplays")){
				getLogger().info("Bacon holograms found");
				Skript.registerEffect(EffTimedHologram.class, "create hologram %string% at %location% for %timespan%");
				Skript.registerEffect(EffTimedBindedHolo.class, "bind hologram %string% to %entity% for %timespan% [offset by %number%, %number%( and|,) %number%]");
				Skript.registerEffect(EffCreateStaticHoloObject.class, "create holo object %string% with id %string% at %location%");
				Skript.registerEffect(EffDeleteHoloObject.class, "delete holo object %string%");
				Skript.registerEffect(EditHoloObject.class, "edit holo object %string% to %string%");
				Skript.registerEffect(EffBoundHoloObject.class, "create bound holo object %string% with id %string% to %entity% [offset by %number%, %number%( and|,) %number%]");
				if (getServer().getPluginManager().isPluginEnabled("ProtocolLib")){
					getLogger().info("Client Side bacon holograms enabled");
					Skript.registerEffect(EffTimedClientSideHolo.class, "display hologram %string% at %location% to %player% for %timespan%");
					Skript.registerEffect(EffCreateStaticClientHoloObject.class, "create client side holo object %string% with id %string% at %location% to %player%");
				}
		}

		 Skript.registerEffect(EffPlaySoundPacket.class,"play %string% to %player% [at volume %number%]");
		 Skript.registerEvent("Crafting Click", EvtCraftClick.class, InventoryClickEvent.class,"crafting click in slot %number%");
		 Skript.registerEvent("On Store", SimpleEvent.class, StoreEvent.class,"(store|chest add)");
		 Skript.registerEvent("On Unstore", SimpleEvent.class, UnstoreEvent.class,"(unstore|chest remove)");
		 new StoreListener(this);
		 EventValues.registerEventValue(StoreEvent.class, ItemStack.class, new Getter<ItemStack, StoreEvent>() {
			    @Nullable
	            @Override
	            public ItemStack get(StoreEvent evt) {
	            	return evt.getItem();
	            }
	        }, 0);
		 EventValues.registerEventValue(StoreEvent.class, Player.class, new Getter<Player, StoreEvent>() {
			    @Nullable
	            @Override
	            public Player get(StoreEvent evt) {
	            	return evt.getPlayer();
	            }
	        }, 0);
		 EventValues.registerEventValue(UnstoreEvent.class, ItemStack.class, new Getter<ItemStack, UnstoreEvent>() {
			    @Nullable
	            @Override
	            public ItemStack get(UnstoreEvent evt) {
	            	return evt.getItem();
	            }
	        }, 0);
		 EventValues.registerEventValue(UnstoreEvent.class, Player.class, new Getter<Player, UnstoreEvent>() {
			    @Nullable
	            @Override
	            public Player get(UnstoreEvent evt) {
	            	return evt.getPlayer();
	            }
	        }, 0);
	 
		 //Made by njol, ported by eyesniper2 to 1.8. All credit goes to njol on this one!
		 Skript.registerEffect(EffMaxHealth.class, "set rf max[imum] h(p|ealth) of %livingentities% to %number%");
		 Skript.registerEffect(EffNameOfScore.class,"set name of sidebar of %player% to %string%");
		 Skript.registerEffect(EffSetScore.class,"set score %string% in sidebar of %player% to %number%");
		 Skript.registerEffect(EffDeleteScore.class,"delete score %string% in sidebar of %player%");
		 Skript.registerEffect(EffRemoveScoreboard.class,"(wipe|erase|delete) %player%['s] sidebar");
		 Skript.registerEffect(EffSetScoreBelowName.class,"set score %string% below %player% to %number% for %player%");
		 Skript.registerEffect(EffRemoveScoreBelowName.class,"(wipe|erase) below score[s] for %player%");
		 Skript.registerEffect(EffSetScoreTab.class,"set tab[list] score of %player% to %number% for %player%");
		 Skript.registerEffect(EffRemoveScoreTab.class,"(wipe|erase|delete) %player%['s] tab[list]");
		 Skript.registerCondition(CondisScoreboardSet.class, "side bar is set for %player%");
		 Skript.registerEffect(EffSetPlayerListName.class, "set %player% tab name to %string%");
		 if (Bukkit.getVersion().contains("1.8")){
			 getLogger().info("Enabling general 1.8 bacon!");
			 Skript.registerExpression(ExprShinyItem.class, ItemStack.class, ExpressionType.PROPERTY, "shiny %itemstacks%");
			 Skript.registerExpression(ExprNoNBT.class, ItemStack.class, ExpressionType.PROPERTY, "%itemstacks% with no nbt"); 
		 }
		 if(Bukkit.getVersion().contains("(MC: 1.8)")){
			 getLogger().info("Getting all the special 1.8 bacon!");
			 Skript.registerEffect(EffTitleV1_8.class,"send %player% title %string% [with subtitle %-string%] [for %-timespan%] [with %-timespan% fade in and %-timespan% fade out]");
			 Skript.registerEffect(EffParticlesV1_8.class, "show %number% %string% particle[s] at %location% for %player% [offset by %number%, %number%( and|,) %number%]");
			 Skript.registerEffect(EffActionBarV1_8.class, "set action bar of %player% to %string%");
			 Skript.registerEffect(EffTabTitlesV1_8.class, "set tab header to %string% and footer to %string% for %player%");
		 }
		 if(Bukkit.getVersion().contains("(MC: 1.8.3)")){
			 getLogger().info("Getting the extra special 1.8.3 bacon!");
			 Skript.registerEffect(EffTitleV1_8_3.class,"send %player% title %string% [with subtitle %-string%] [for %-timespan%] [with %-timespan% fade in and %-timespan% fade out]");
			 Skript.registerEffect(EffParticlesV1_8_3.class, "show %number% %string% particle[s] at %location% for %player% [offset by %number%, %number%( and|,) %number%]");
			 Skript.registerEffect(EffActionBarV1_8_3.class, "set action bar of %player% to %string%");
			 Skript.registerEffect(EffTabTitlesV1_8_3.class, "set tab header to %string% and footer to %string% for %player%");
		 }
		 if(Bukkit.getVersion().contains("(MC: 1.8.4)") || Bukkit.getVersion().contains("(MC: 1.8.5)") || Bukkit.getVersion().contains("(MC: 1.8.6)") || Bukkit.getVersion().contains("(MC: 1.8.7)")){
			 getLogger().info("Getting the extra special 1.8.4 - 1.8.7 bacon!");
			 Skript.registerEffect(EffTitleV1_8_4.class,"send %player% title %string% [with subtitle %-string%] [for %-timespan%] [with %-timespan% fade in and %-timespan% fade out]");
			 Skript.registerEffect(EffParticlesV1_8_4.class, "show %number% %string% particle[s] at %location% for %player% [offset by %number%, %number%( and|,) %number%]");
			 Skript.registerEffect(EffActionBarV1_8_4.class, "set action bar of %player% to %string%");
			 Skript.registerEffect(EffTabTitlesV1_8_4.class, "set tab header to %string% and footer to %string% for %player%");
		 }
		 enableFastScoreboards = this.getConfig().getBoolean("enableFastScoreBoards");
		 getLogger().info("Bacon is ready!");
	 }
	 
	 @Override
	    public void onDisable(){
		 getLogger().info("Bacon has been eaten. Make some more soon!");
	 }
	 
	 @EventHandler
	 public void onJoin(PlayerJoinEvent evt){
		 if (enableFastScoreboards == true){
			 Player player = evt.getPlayer();
			 ScoreboardManager manager = Bukkit.getScoreboardManager();
			 Scoreboard board = manager.getNewScoreboard();
			 player.setScoreboard(board);
		 }
	 }
	 
	 
	 }

