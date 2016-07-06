package net.rayfall.eyesniper2.skrayfall.scoreboard;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.DisplaySlot;
import org.eclipse.jdt.annotation.Nullable;

public class EffDeleteScore extends Effect {

  // delete score %string% in sidebar of %player%
  private Expression<String> name;
  private Expression<Player> player;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    name = (Expression<String>) exp[0];
    player = (Expression<Player>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (!(player.getSingle(evt).isOnline())) {
      Skript.error("The player is not online!");
    } else {
      if (player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.SIDEBAR) != null) {
        if (player.getSingle(evt).getScoreboard().getObjective(DisplaySlot.SIDEBAR)
            .getScore(name.getSingle(evt).replace("\"", "")) != null) {
          player.getSingle(evt).getScoreboard().resetScores(name.getSingle(evt).replace("\"", ""));
        } else {
          Skript.error("That score does not exist!");
        }
      } else {
        Skript.error("No sidebar score has been set!");
      }



    }


  }

}
