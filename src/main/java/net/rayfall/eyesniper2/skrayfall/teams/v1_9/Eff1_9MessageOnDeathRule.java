package net.rayfall.eyesniper2.skrayfall.teams.v1_9;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

import net.rayfall.eyesniper2.skrayfall.Core;

import org.bukkit.event.Event;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;
import org.eclipse.jdt.annotation.Nullable;

public class Eff1_9MessageOnDeathRule extends Effect {

    // (show|display) death message[s] %teamoptionstatus% for team %string%

    private Expression<OptionStatus> status;
    private Expression<String> team;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        status = (Expression<OptionStatus>) exp[0];
        team = (Expression<String>) exp[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        if (team != null) {
            Core.teamManager.setTeamOption(team.getSingle(evt).replace("\"", ""),
                    Option.DEATH_MESSAGE_VISIBILITY, status.getSingle(evt));
        }
    }
}
