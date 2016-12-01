package net.rayfall.eyesniper2.skrayfall.voting;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RayFallVoteReceivedEvent extends Event {
  private static final HandlerList handlers = new HandlerList();
  private String voter;
  private String site;

  @Override
  public HandlerList getHandlers() {
    return handlers;
  }

  public static HandlerList getHandlerList() {
    return handlers;
  }

  public RayFallVoteReceivedEvent(String voter, String site) {
    this.voter = voter;
    this.site = site;
  }

  public String getVotersName() {
    return this.voter;
  }

  public String getSite() {
    return this.site;
  }


}
