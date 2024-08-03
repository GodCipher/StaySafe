package de.godcipher.staysafe;

import de.godcipher.staysafe.tracker.ActivityTracker;
import de.godcipher.staysafe.listener.PlayerListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaySafe extends JavaPlugin {

  private final ActivityTracker activityTracker = new ActivityTracker();

  @Override
  public void onEnable() {
    setupBStats();
    loadConfig();
    loadConfigValues();
    registerListener();
  }

  @Override
  public void onDisable() {
    activityTracker.clear();
  }

  private void registerListener() {
    Bukkit.getPluginManager().registerEvents(new PlayerListener(activityTracker), this);
  }

  private void loadConfig() {
    getConfig().options().copyDefaults(true);
    saveDefaultConfig();
  }

  private void loadConfigValues() {
    activityTracker.setInactiveTime(getConfig().getLong("inactive-time") * 60 * 1000);
  }

  private void setupBStats() {
    new Metrics(this, 22872);
  }
}
