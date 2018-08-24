package net.rayfall.eyesniper2.skrayfall.bossbar;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Add Player to ID Based Bossbar")
@Description("Add a player to an ID based Bossbar.")
public class EffSetBossBar extends Effect {

    // (add|set) bossbar %string% for %player%

    private Expression<String> id;
    private Expression<Player> players;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        id = (Expression<String>) exp[0];
        players = (Expression<Player>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }


    @Override
    protected void execute(Event evt) {
        Core.bossbarManager.addPlayers(id.getSingle(evt).replace("\"", ""), players.getAll(evt));
    }
}
