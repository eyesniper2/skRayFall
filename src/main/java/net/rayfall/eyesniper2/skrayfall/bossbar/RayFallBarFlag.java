package net.rayfall.eyesniper2.skrayfall.bossbar;

import org.bukkit.boss.BarFlag;

public enum RayFallBarFlag {

    CREATE_FOG(BarFlag.CREATE_FOG), DARKEN_SKY(BarFlag.DARKEN_SKY), PLAY_BOSS_MUSIC(
            BarFlag.PLAY_BOSS_MUSIC);

    private BarFlag key;

    RayFallBarFlag(BarFlag flag) {
        this.key = flag;
    }

    public BarFlag getKey() {
        return this.key;
    }

}
