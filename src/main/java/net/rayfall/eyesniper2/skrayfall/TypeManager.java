package net.rayfall.eyesniper2.skrayfall;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarColor;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarFlag;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarStyle;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jdt.annotation.Nullable;

public class TypeManager implements SyntaxManagerInterface{

    private JavaPlugin plugin;

    TypeManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void registerSyntax(){
        if (Skript.isAcceptRegistrations()) {
            if (Skript.isRunningMinecraft(1, 9)) {
                registerV1_9Elements();
            }
        } else {
            plugin.getLogger().info("skRayFall was unable to register some extra types.");
        }
    }

    private void registerV1_9Elements() {
        Classes.registerClass(new ClassInfo<>(RayFallBarColor.class, "bossbarcolor")
                .parser(new Parser<RayFallBarColor>() {
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    @Nullable
                    public RayFallBarColor parse(String value, ParseContext cont) {
                        try {
                            return RayFallBarColor.valueOf(value.replace(" ", "_").trim().toUpperCase());
                        } catch (IllegalArgumentException exception) {
                            return null;
                        }
                    }

                    @Override
                    public String toString(RayFallBarColor eff, int in) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }

                    @Override
                    public String toVariableNameString(RayFallBarColor eff) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }
                })
                .name("Bossbar Color")
                .description("A data type to call the colors for the new 1.9 bossbars.")
                .usage("blue, green, pink, purple, red, white or yellow")
        );
        Classes.registerClass(new ClassInfo<>(RayFallBarFlag.class, "bossbarflag")
                .parser(new Parser<RayFallBarFlag>() {
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    @Nullable
                    public RayFallBarFlag parse(String value, ParseContext cont) {
                        try {
                            return RayFallBarFlag.valueOf(value.replace(" ", "_").trim().toUpperCase());
                        } catch (IllegalArgumentException exception) {
                            return null;
                        }
                    }

                    @Override
                    public String toString(RayFallBarFlag eff, int in) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }

                    @Override
                    public String toVariableNameString(RayFallBarFlag eff) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }
                })
                .name("Bossbar Flag")
                .description("Bar Flags to support the new 1.9 bossbars.")
                .usage("create fog, darken sky or play boss music")
        );
        Classes.registerClass(new ClassInfo<>(RayFallBarStyle.class, "bossbarstyle")
                .parser(new Parser<RayFallBarStyle>() {
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    @Nullable
                    public RayFallBarStyle parse(String value, ParseContext cont) {
                        try {
                            return RayFallBarStyle.valueOf(value.replace(" ", "_").trim().toUpperCase());
                        } catch (IllegalArgumentException exception) {
                            return null;
                        }
                    }

                    @Override
                    public String toString(RayFallBarStyle eff, int in) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }

                    @Override
                    public String toVariableNameString(RayFallBarStyle eff) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }
                })
                .name("Bossbar Style")
                .description("Bossbar styles to support the new 1.9 BossBar.")
                .usage("segmented 10, segmented 12, segmented 20, segmented 6 and solid")
        );

    }
}
