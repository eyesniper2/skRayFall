package net.rayfall.eyesniper2.skrayfall.general.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Convert Text to Location")
@Description("Convert a comma separated string of x, y and z cords into a location. " +
        "eg. \"1.3,4.5,1.5\" to a location")
public class ExprTextToLocation extends SimpleExpression<Location> {

    // %string% converted to location

    private Expression<String> text;

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        text = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    @Nullable
    protected Location[] get(Event evt) {
        if (text != null) {
            String[] parts = text.getSingle(evt).replace("\"", "").split(":");
            World world = Bukkit.getWorld(parts[0]);
            if (world == null) {
                return null;
            }
            String[] cords = parts[1].split(",");
            if (cords.length > 3) {
                return null;
            }
            Location loc;
            try {
                loc = new Location(world, Double.parseDouble(cords[0]), Double.parseDouble(cords[1]),
                        Double.parseDouble(cords[2]));
            } catch (NumberFormatException exception) {
                return null;
            } catch (NullPointerException exception) {
                return null;
            }
            return new Location[]{loc};
        }
        return null;
    }
}
