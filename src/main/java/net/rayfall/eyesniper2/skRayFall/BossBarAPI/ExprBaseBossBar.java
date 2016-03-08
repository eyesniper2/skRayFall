package net.rayfall.eyesniper2.skRayFall.BossBarAPI;

import org.bukkit.entity.Player;
import org.eclipse.jdt.annotation.Nullable;
import org.inventivetalent.bossbar.BossBarAPI;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprBaseBossBar extends SimplePropertyExpression<Player, Number>{

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	@Nullable
	public Number convert(Player evt) {
		return BossBarAPI.getBossBar(evt).getMaxHealth();
	}

	@Override
	protected String getPropertyName() {
		return null;
	}

}
