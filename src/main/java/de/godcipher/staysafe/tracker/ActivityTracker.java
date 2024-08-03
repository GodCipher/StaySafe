package de.godcipher.staysafe.tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ActivityTracker {

  private final Map<UUID, Long> lastActivityMap = new HashMap<>();
  private long inactiveTime = 5 * 60 * 1000;

  public void updateActivity(UUID player) {
    lastActivityMap.put(player, System.currentTimeMillis());
  }

  public void remove(UUID player) {
    lastActivityMap.remove(player);
  }

  public void setInactiveTime(long inactiveTime) {
    this.inactiveTime = inactiveTime;
  }

  public boolean isInactive(UUID player) {
    return System.currentTimeMillis() - lastActivityMap.getOrDefault(player, 0L) > inactiveTime;
  }

  public void clear() {
    lastActivityMap.clear();
  }
}
