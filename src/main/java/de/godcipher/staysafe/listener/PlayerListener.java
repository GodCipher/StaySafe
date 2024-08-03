package de.godcipher.staysafe.listener;

import de.godcipher.staysafe.tracker.ActivityTracker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

  private final ActivityTracker activityTracker;

  public PlayerListener(ActivityTracker activityTracker) {
    this.activityTracker = activityTracker;
  }

  @EventHandler
  public void onMove(PlayerMoveEvent event) {
    activityTracker.updateActivity(event.getPlayer().getUniqueId());
  }

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    activityTracker.updateActivity(event.getPlayer().getUniqueId());
  }

  @EventHandler
  public void onTarget(EntityTargetLivingEntityEvent event) {
    if (event.getTarget() == null) {
      return;
    }
    if (event.getTarget().isDead()) {
      return;
    }
    if (activityTracker.isInactive(event.getTarget().getUniqueId())) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    activityTracker.remove(event.getPlayer().getUniqueId());
  }
}
