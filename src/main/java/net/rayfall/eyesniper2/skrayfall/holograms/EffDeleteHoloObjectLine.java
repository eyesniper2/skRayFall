package net.rayfall.eyesniper2.skrayfall.holograms;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

public class EffDeleteHoloObjectLine extends Effect {

  // (delete|remove) line %number% in holo object %string%

  private Expression<Number> line;
  private Expression<String> id;

  @SuppressWarnings("unchecked")
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    line = (Expression<Number>) exp[0];
    id = (Expression<String>) exp[1];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    if (HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", "")) != null) {
      Hologram hologram = HoloManager.getFromHoloMap(id.getSingle(evt).replace("\"", ""));
      if (hologram.getHeight() > line.getSingle(evt).doubleValue()) {
        hologram.removeLine(line.getSingle(evt).intValue());
      }

    }

  }

}
