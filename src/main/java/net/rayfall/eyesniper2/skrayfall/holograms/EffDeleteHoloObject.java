package net.rayfall.eyesniper2.skrayfall.holograms;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import com.gmail.filoghost.holographicdisplays.api.Hologram;

import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Delete Object Based Holograms")
@Description("Delete object based holograms by id.")
@RequiredPlugins("Holographic Displays")
public class EffDeleteHoloObject extends Effect {

    // delete holo object %string%

    private Expression<String> id;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Hologram holo = HoloManager.removeFromHoloMap(id.getSingle(evt).replace("\"", ""));
        if (holo != null) {
            holo.delete();
        }
    }
}
