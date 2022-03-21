package net.rayfall.eyesniper2.skrayfall.v1_17;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.DocumentationId;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.util.Utils;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.eclipse.jdt.annotation.Nullable;

@Name("Set Tab Header & Footer")
@Description({"Modify Tab by:",
        "* Header",
        "* Footer",
        "* Player",
        "This will display a client side header and footer for a player"})
@DocumentationId("EffTabTitles")
public class EffTabTitlesV1_17 extends Effect {

    // set tab header to %string% and footer to %string% for %player%

    private Expression<Player> playerExpr;
    private Expression<String> coreHeader;
    private Expression<String> coreFooter;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] exp, int arg1, Kleenean arg2, ParseResult arg3) {
        coreHeader = (Expression<String>) exp[0];
        coreFooter = (Expression<String>) exp[1];
        playerExpr = (Expression<Player>) exp[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event arg0, boolean arg1) {
        return null;
    }

    @Override
    protected void execute(Event evt) {
        Player player = playerExpr.getSingle(evt);

        if (player == null) {
            Skript.warning("[TabTitle] Player is not online");
            return;
        }

        String header = Utils.replaceChatStyles(coreHeader.getSingle(evt).replace("\"", ""));
        String footer = Utils.replaceChatStyles(coreFooter.getSingle(evt).replace("\"", ""));
        player.setPlayerListHeaderFooter(header, footer);
    }
}