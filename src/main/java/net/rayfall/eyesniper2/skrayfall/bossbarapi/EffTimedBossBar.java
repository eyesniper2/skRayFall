package net.rayfall.eyesniper2.skrayfall.bossbarapi;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Timespan;
import ch.njol.util.Kleenean;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

@Name("Set Boss Bar")
@Description("Set a timed or static bossbar.")
@RequiredPlugins("BossBarAPI")
@DocumentationId("SetBossBarAPIBossBar")
public class EffTimedBossBar extends Effect {

    // display bossbar with "text" to player for %timespan%
    private Expression<Player> player;
    private Expression<String> text;
    private Expression<Timespan> time;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        text = (Expression<String>) exp[0];
        player = (Expression<Player>) exp[1];
        time = (Expression<Timespan>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void execute(Event evt) {
        BossBarAPI.setMessage(player.getSingle(evt), text.getSingle(evt).replace("\"", ""),
                100, time.getSingle(evt).getTicks() / 20);
        // BossBarAPI.addBar(player.getSingle(evt), new TextComponent(text.getSingle(evt).replace("\"",
        // "")),null,null, 1, t.getSingle(evt).getTicks()/20,5);
    }
}
