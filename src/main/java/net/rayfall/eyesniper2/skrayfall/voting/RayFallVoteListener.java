package net.rayfall.eyesniper2.skrayfall.voting;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import ch.njol.skript.Skript;

import com.vexsoftware.votifier.model.VotifierEvent;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class RayFallVoteListener implements Listener {

  private static Multimap<String, OfflineVote> voteMap = HashMultimap.create();

  public RayFallVoteListener(Plugin core) {
    core.getServer().getPluginManager().registerEvents(this, core);
  }

  /**
   * Listener used to generate the RayFallVoteEvent and RayFallOfflineVoteEvent. 
   * 
   * @param votifierEvent The initial VotifierEvent event 
   *     used to generate the skRayFall vote events
   */
  @EventHandler
  public void onVote(VotifierEvent votifierEvent) {
    String name = votifierEvent.getVote().getUsername();
    RayFallVoteReceivedEvent voteReceivedEvent = new RayFallVoteReceivedEvent(name,
        votifierEvent.getVote().getServiceName());
    Bukkit.getPluginManager().callEvent(voteReceivedEvent);
    if (Bukkit.getPlayer(name) != null && Bukkit.getPlayer(name).isOnline()) {
      RayFallVoteEvent event =
          new RayFallVoteEvent(Bukkit.getPlayer(name), votifierEvent.getVote().getServiceName());
      Bukkit.getPluginManager().callEvent(event);
    } else {
      // Store player and vote in the hashMap
      voteMap.put(name, new OfflineVote(votifierEvent.getVote().getServiceName(),
          votifierEvent.getVote().getTimeStamp()));
      if (Bukkit.getPlayer(name) != null && !Bukkit.getPlayer(name).isOnline()) {
        RayFallOfflineVoteEvent offlineEvent = new RayFallOfflineVoteEvent(Bukkit.getPlayer(name),
            votifierEvent.getVote().getServiceName());
        Bukkit.getPluginManager().callEvent(offlineEvent);
      }
      Skript.error("Player is not online, saving vote for the next time they are online");

    }
  }

  /**
   * Listener used to generate the RayFallVoteEvent when a player joins to redeem their reward. 
   * 
   * @param evt The initial PlayerJoinEvent event 
   *     used to generate the RayFallVoteEvent event
   */
  @EventHandler
  public void triggerVoteOnJoin(PlayerJoinEvent evt) {
    Player player = evt.getPlayer();
    if (voteMap.containsKey(player.getName())) {
      Collection<OfflineVote> listOfVotes = voteMap.get(player.getName());
      for (OfflineVote vote : listOfVotes) {
        RayFallVoteEvent event = new RayFallVoteEvent(player, vote.getSite());
        Bukkit.getPluginManager().callEvent(event);
      }
      voteMap.removeAll(player.getName());
    }
  }


}
