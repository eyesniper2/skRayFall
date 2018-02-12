package net.rayfall.eyesniper2.skrayfall;

import ch.njol.skript.Skript;
import de.slikey.effectlib.EffectManager;
import net.coreprotect.CoreProtectAPI;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateResult;
import net.rayfall.eyesniper2.skrayfall.bossbar.BossBarManager;
import net.rayfall.eyesniper2.skrayfall.bossbarapi.BossBarApiSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.capes.CapesSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.citizens.CitizensSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.commands.GeneralCommands;
import net.rayfall.eyesniper2.skrayfall.coreprotect.CoreProtectSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.crackshot.CrackshotSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.effectlibsupport.EffectLibSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.effectlibsupport.RayFallEffectManager;
import net.rayfall.eyesniper2.skrayfall.general.GeneralSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.holograms.HoloManager;
import net.rayfall.eyesniper2.skrayfall.holograms.HologramSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.scoreboard.IdScoreBoardManager;
import net.rayfall.eyesniper2.skrayfall.scoreboard.ScoreboardSyntaxManager;
import net.rayfall.eyesniper2.skrayfall.teams.TeamManager;
import net.rayfall.eyesniper2.skrayfall.voting.VotifierSyntaxManager;
import org.bstats.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Core extends JavaPlugin implements Listener {

  public static EffectManager effectManager;
  public static Plugin plugin = null;
  public static RayFallEffectManager rayfallEffectManager;
  public static IdScoreBoardManager sbManager;
  public boolean enableFastScoreboards = true;
  public static CoreProtectAPI skCoreProtect = null;
  public static TeamManager teamManager;
  public static BossBarManager bossbarManager;

  @Override
  public void onEnable() {
    getLogger().info("Yay! You are running skRayFall " + this.getDescription().getVersion() + "!");
    getLogger().info("Nathan and Lewis <3 you.");

    getServer().getPluginManager().registerEvents(this, this);
    Skript.registerAddon(this);
    saveDefaultConfig();

    this.getCommand("skrayfall").setExecutor(new GeneralCommands(this));
    if (plugin == null) {
      plugin = this;
    }

    getLogger().info("Cooking Bacon...");

    Metrics metrics = new Metrics(this);

    // Register all types
    TypeManager typeManager = new TypeManager(this);
    typeManager.registerSyntax();

    CitizensSyntaxManager citizensSyntaxManager = new CitizensSyntaxManager(this);
    citizensSyntaxManager.registerSyntax();

    EffectLibSyntaxManager effectLibSyntaxManager = new EffectLibSyntaxManager(this);
    effectLibSyntaxManager.registerSyntax();
    effectManager = effectLibSyntaxManager.getEffectManager();
    rayfallEffectManager = effectLibSyntaxManager.getRayfallEffectManager();

    VotifierSyntaxManager votifierSyntaxManager = new VotifierSyntaxManager(this);
    votifierSyntaxManager.registerSyntax();

    HologramSyntaxManager hologramSyntaxManager = new HologramSyntaxManager(this);
    hologramSyntaxManager.registerSyntax();

    CrackshotSyntaxManager crackshotSyntaxManager = new CrackshotSyntaxManager(this);
    crackshotSyntaxManager.registerSyntax();

    BossBarApiSyntaxManager bossBarApiSyntaxManager = new BossBarApiSyntaxManager(this);
    bossBarApiSyntaxManager.registerSyntax();

    CapesSyntaxManager capesSyntaxManager = new CapesSyntaxManager(this);
    capesSyntaxManager.registerSyntax();

    CoreProtectSyntaxManager coreProtectSyntaxManager = new CoreProtectSyntaxManager(this);
    coreProtectSyntaxManager.registerSyntax();
    skCoreProtect = coreProtectSyntaxManager.getSkCoreProtect();

    ScoreboardSyntaxManager scoreboardSyntaxManager = new ScoreboardSyntaxManager(this);
    scoreboardSyntaxManager.registerSyntax();
    sbManager = scoreboardSyntaxManager.getIdScoreBoardManager();

    GeneralSyntaxManager generalSyntaxManager = new GeneralSyntaxManager(this);
    generalSyntaxManager.registerSyntax();

    VersionedGeneralSyntaxManager versionedGeneralSyntaxManager = new VersionedGeneralSyntaxManager(this);
    versionedGeneralSyntaxManager.registerSyntax();

    enableFastScoreboards = this.getConfig().getBoolean("enableFastScoreBoards");
    if (getConfig().getBoolean("UpdateAlerts", false)) {
      Updater updater =
          new Updater(this, 88677, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, true);
      if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
        getServer().getConsoleSender().sendMessage("[" + ChatColor.DARK_AQUA + "skRayFall"
            + ChatColor.RESET + "] " + ChatColor.RED + "An update for skRayFall is available!");
      }
    }
    getLogger().info("Bacon is ready!");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bacon has been eaten. Make some more soon!");
    HoloManager.dumpHoloMap();
    rayfallEffectManager.dumpData();
    sbManager.dumpData();
    if (bossbarManager != null) {
      bossbarManager.dumpData();
    }
  }
  
  /**
   * Generates a new private scoreboard for the player when they join the server if
   * enableFastScoreboards is set to true.
   * 
   *
   * @param evt The Player Join Event
   */
  @EventHandler
  public void onJoinScoreboards(PlayerJoinEvent evt) {
    if (enableFastScoreboards) {
      Player player = evt.getPlayer();
      ScoreboardManager manager = Bukkit.getScoreboardManager();
      Scoreboard board = manager.getNewScoreboard();
      player.setScoreboard(board);
    }
  }
}
