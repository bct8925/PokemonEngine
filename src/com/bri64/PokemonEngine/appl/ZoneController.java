package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.Zone;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private Map<String, Zone> zones;
  private Zone currentZone;

  public ZoneController() {
    this.zones = new HashMap<>();

    // Load zones

    // Set current zone
  }

  public Zone getZone(final String ID) {
    return zones.get(ID);
  }
  public Zone getCurrentZone() {
    return currentZone;
  }
  public void setCurrentZone(final String ID) {
    currentZone = getZone(ID);
  }

}
