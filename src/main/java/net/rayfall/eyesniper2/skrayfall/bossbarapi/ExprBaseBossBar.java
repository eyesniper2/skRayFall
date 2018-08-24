package net.rayfall.eyesniper2.skrayfall.bossbarapi;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.RequiredPlugins;
import ch.njol.skript.expressions.base.SimplePropertyExpression;

import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

@Name("Boss Bar Value")
@Description("Gets the value of a bossbar (0-100) or -1 if the player does not have a bossbar.")
@RequiredPlugins("BossBarAPI")
public class ExprBaseBossBar extends SimplePropertyExpression<Player, Number> {

    @Override
    public Class<? extends Number> getReturnType() {
        return Number.class;
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nullable
    public Number convert(Player evt) {
        if (BossBarAPI.getBossBar(evt) != null){
            return BossBarAPI.getBossBar(evt).getMaxHealth();
        }
        Skript.error("Unable to get bossbar for player " + evt.getDisplayName());
        return -1;

        /*
         * for(BossBar bar : BossBarAPI.getBossBars(evt)){ return ((bar.getProgress()-1) * 100); }
         * return null;
         */
    }

    @Override
    protected String getPropertyName() {
        return null;
    }
}
