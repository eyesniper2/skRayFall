package net.rayfall.eyesniper2.skrayfall.citizens.effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.jrbudda.builder.BuilderTrait;
import net.jrbudda.builder.MCEditSchematicFormat;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

import java.io.File;
import java.io.IOException;

public class EffStartBuilderBuild extends Effect {
  // make citizen %number% build %schematic% at %location% [speed %number%] for %player%
  private Expression<Number> id;
  private Expression<String> schematic;
  private Expression<Location> loc;
  private Expression<Number> speed;
  private Expression<Player> player;

  @SuppressWarnings("unchecked")
  @Nullable
  @Override
  public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
    id = (Expression<Number>) exp[0];
    schematic = (Expression<String>) exp[1];
    loc = (Expression<Location>) exp[2];
    speed = (Expression<Number>) exp[3];
    player = (Expression<Player>) exp[4];
    return true;
  }

  @Override
  public String toString(@Nullable Event arg0, boolean arg1) {
    return null;
  }

  @Override
  protected void execute(Event evt) {
    NPCRegistry registry = CitizensAPI.getNPCRegistry();
    NPC npc = registry.getById(id.getSingle(evt).intValue());
    npc.addTrait(BuilderTrait.class);
    npc.teleport(loc.getSingle(evt), null);
    if (speed != null) {
      npc.getNavigator().getDefaultParameters().baseSpeed(speed.getSingle(evt).floatValue());
    }
    BuilderTrait bt = npc.getTrait(BuilderTrait.class);
    bt.oncancel = null;
    bt.oncomplete = null;
    bt.onStart = null;
    bt.ContinueLoc = null;
    bt.IgnoreAir = false;
    bt.IgnoreLiquid = false;
    bt.Excavate = false;
    bt.GroupByLayer = true;
    bt.BuildYLayers = 1;
    bt.BuildPatternXY = net.jrbudda.builder.BuilderTrait.BuildPatternsXZ.spiral;
    File file = new File("plugins/Builder/schematics/");
    try {
      bt.schematic =
          MCEditSchematicFormat.load(file, schematic.getSingle(evt).trim().replace("\"", ""));
    } catch (IOException exception) {
      exception.printStackTrace();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    bt.TryBuild(player.getSingle(evt));
  }

}
