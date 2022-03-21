package net.rayfall.eyesniper2.skrayfall.v1_17;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.skript.util.Utils;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Send Title")
@Description({"Set title by:",
        "* Name",
        "* Subtitle",
        "* Player",
        "* Time",
        "* Fadein",
        "* Fadeout",
        "This will send a title to a player with an optional subtitle and display time"})
@DocumentationId("EffTitle")
public class EffTitleV1_17 extends Effect {
    // send %player% title %string% [with subtitle %string%] for %timespan% [with %timespan% fade in
    // and %timespan% fade out]

    private Expression<String> titleExpr;
    private Expression<String> subTitleExpr;
    private Expression<Player> player;
    private Expression<Timespan> timeExpr;
    private Expression<Timespan> fadeInExpr;
    private Expression<Timespan> fadeOutExpr;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        player = (Expression<Player>) exp[0];
        titleExpr = (Expression<String>) exp[1];
        subTitleExpr = (Expression<String>) exp[2];
        timeExpr = (Expression<Timespan>) exp[3];
        fadeInExpr = (Expression<Timespan>) exp[4];
        fadeOutExpr = (Expression<Timespan>) exp[5];
        return true;
    }

    @Override
    public String toString(@Nullable Event evt, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        String title = titleExpr.getSingle(evt) != null ? Utils.replaceChatStyles(titleExpr.getSingle(evt)) : "";
        String subTitle = subTitleExpr.getSingle(evt) != null ?
                Utils.replaceChatStyles(subTitleExpr.getSingle(evt)) : null;

        int fadeIn = (fadeInExpr != null && fadeInExpr.getSingle(evt) != null) ? (int) fadeInExpr.getSingle(evt).getTicks_i() : 10;
        int timeTick = (timeExpr != null && timeExpr.getSingle(evt) != null) ? (int) timeExpr.getSingle(evt).getTicks_i() : 70;
        int fadeOut = (fadeOutExpr != null && fadeOutExpr.getSingle(evt) != null) ? (int) fadeOutExpr.getSingle(evt).getTicks_i() : 20;

        for (Player p : player.getArray(evt)) {
            p.sendTitle(title, subTitle, fadeIn, timeTick, fadeOut);
        }
    }
}