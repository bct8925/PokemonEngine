package bri64.PokemonEngine.appl;

import bri64.PokemonEngine.model.Zone;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private Map<String, Zone> loadedZones;
  private Zone currentZone;

  public ZoneController() {
    this.loadedZones = new HashMap<>();
    this.currentZone = new Zone();

    // Load loadedZones

    // Set current zone
  }

  public Zone getZone(final String ID) {
    return loadedZones.get(ID);
  }
  public Zone getCurrentZone() {
    return currentZone;
  }
  public void setCurrentZone(final String ID) {
    currentZone = getZone(ID);
  }

}
