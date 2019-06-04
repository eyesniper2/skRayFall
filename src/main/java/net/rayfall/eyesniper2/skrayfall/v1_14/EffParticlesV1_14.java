package net.rayfall.eyesniper2.skrayfall.v1_14;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Show Particle")
@Description({"Show particle by:",
        "* Player",
        "* Location",
        "* Amount",
        "* Particle Type",
        "This will display a particle at a given location for a player. This is client side!",
        "[List of particles](https://gist.github.com/eyesniper2/935315532ef05fc56656)"})
@DocumentationId("EffParticles")
public class EffParticlesV1_14 extends Effect {
    // show %number% of %string% particle[s] at %location% for %player% [offset with %number%,
    // %number% (and|,) %number%]
    private Expression<Number> partNum;
    private Expression<Player> player;
    private Expression<Location> location;
    private Expression<String> type;
    private Expression<Number> xoffset;
    private Expression<Number> yoffset;
    private Expression<Number> zoffset;


    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        partNum = (Expression<Number>) exp[0];
        type = (Expression<String>) exp[1];
        location = (Expression<Location>) exp[2];
        xoffset = (Expression<Number>) exp[4];
        yoffset = (Expression<Number>) exp[5];
        zoffset = (Expression<Number>) exp[6];
        player = (Expression<Player>) exp[3];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        double hx = 0;
        double hy = 0;
        double hz = 0;
        //float id = 0;
        //int[] array = new int[0];
        if (xoffset != null) {
            hx = xoffset.getSingle(evt).doubleValue();
        }
        if (yoffset != null) {
            hy = yoffset.getSingle(evt).doubleValue();
        }
        if (zoffset != null) {
            hz = zoffset.getSingle(evt).doubleValue();
        }
        String core = type.getSingle(evt);
    /*
    if (core.toUpperCase().replace(" ", "_").contains("BLOCK_CRACK")
        || core.toUpperCase().replace(" ", "_").contains("BLOCK_DUST")) {
      int index = type.getSingle(evt).lastIndexOf("_");
      try {
        id = Integer.parseInt(type.getSingle(evt).substring(index + 1));
      } catch (Exception exception) {
        Skript.error("Could not parse datavalue!");
        id = 0;
      }
      core = core.substring(0, index);
      array = new int[1];
    }*/
        Particle particle;
        try {
            particle = Particle.valueOf(core);
        } catch (Exception e) {
            Skript.error("Could not parse particle value!");
            return;
        }
        player.getSingle(evt).spawnParticle(particle, location.getSingle(evt),
                partNum.getSingle(evt).intValue(), hx, hy, hz);
    }
}