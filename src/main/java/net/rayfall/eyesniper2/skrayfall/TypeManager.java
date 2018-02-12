package net.rayfall.eyesniper2.skrayfall;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import de.slikey.effectlib.util.ParticleEffect;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarColor;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarFlag;
import net.rayfall.eyesniper2.skrayfall.bossbar.RayFallBarStyle;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Team;
import org.eclipse.jdt.annotation.Nullable;

public class TypeManager implements SyntaxManagerInterface{

    private JavaPlugin plugin;

    TypeManager(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void registerSyntax(){
        if (Skript.isAcceptRegistrations()) {
            registerTypes();
            if (Bukkit.getVersion().contains("1.9") || Bukkit.getVersion().contains("1.10")
                    || Bukkit.getVersion().contains("1.11") || Bukkit.getVersion().contains("1.12")){
                registerV1_9Elements();
                if (!(Bukkit.getVersion().contains("(MC: 1.9)")
                        || Bukkit.getVersion().contains("(MC: 1.9.1)"))) {
                    registerV1_9_2Elements();
                } else {
                    registerNon1_9_2TeamElements();
                }
            } else if (Bukkit.getVersion().contains("1.8")) {
                registerNon1_9_2TeamElements();
            }
        } else {
            plugin.getLogger().info("skRayFall was unable to register some extra types.");
        }
    }

    private void registerTypes() {
        Classes.registerClass(new ClassInfo<ParticleEffect>(ParticleEffect.class, "effectlibparticle")
                .parser(new Parser<ParticleEffect>() {
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    @Nullable
                    public ParticleEffect parse(String value, ParseContext cont) {
                        try {
                            return ParticleEffect.valueOf(value.replace(" ", "_").trim().toUpperCase());
                        } catch (IllegalArgumentException exception) {
                            return null;
                        }
                    }

                    @Override
                    public String toString(ParticleEffect eff, int in) {
                        return eff.getName().replace("_", " ").toLowerCase();
                    }

                    @Override
                    public String toVariableNameString(ParticleEffect eff) {
                        return eff.getName().replace("_", " ").toLowerCase();
                    }
                }));

    }

    private void registerNon1_9_2TeamElements() {
        Classes.registerClass(
                new ClassInfo<NameTagVisibility>(NameTagVisibility.class, "nametagvisibility")
                        .parser(new Parser<NameTagVisibility>() {
                            @Override
                            public String getVariableNamePattern() {
                                return ".+";
                            }

                            @Override
                            @Nullable
                            public NameTagVisibility parse(String value, ParseContext cont) {
                                try {
                                    return NameTagVisibility.valueOf(value.replace(" ", "_").trim().toUpperCase());
                                } catch (IllegalArgumentException exception) {
                                    return null;
                                }
                            }

                            @Override
                            public String toString(NameTagVisibility name, int in) {
                                return name.name().replace("_", " ").toLowerCase();
                            }

                            @Override
                            public String toVariableNameString(NameTagVisibility name) {
                                return name.name().replace("_", " ").toLowerCase();
                            }
                        }));
    }


    private void registerV1_9Elements() {
        Classes.registerClass(new ClassInfo<RayFallBarColor>(RayFallBarColor.class, "bossbarcolor")
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
                }));
        Classes.registerClass(new ClassInfo<RayFallBarFlag>(RayFallBarFlag.class, "bossbarflag")
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
                }));
        Classes.registerClass(new ClassInfo<RayFallBarStyle>(RayFallBarStyle.class, "bossbarstyle")
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
                }));

    }

    private void registerV1_9_2Elements() {
        // TODO: Test 1.9.2 Team stuff
        Classes.registerClass(new ClassInfo<Team.OptionStatus>(Team.OptionStatus.class, "teamoptionstatus")
                .parser(new Parser<Team.OptionStatus>() {
                    @Override
                    public String getVariableNamePattern() {
                        return ".+";
                    }

                    @Override
                    @Nullable
                    public Team.OptionStatus parse(String value, ParseContext cont) {
                        try {
                            return Team.OptionStatus.valueOf(value.replace(" ", "_").trim().toUpperCase());
                        } catch (IllegalArgumentException exception) {
                            return null;
                        }
                    }

                    @Override
                    public String toString(Team.OptionStatus eff, int in) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }

                    @Override
                    public String toVariableNameString(Team.OptionStatus eff) {
                        return eff.name().replace("_", " ").toLowerCase();
                    }
                }));
    }
}
