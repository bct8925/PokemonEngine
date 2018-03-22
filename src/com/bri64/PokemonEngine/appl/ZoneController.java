package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.Game;
import com.bri64.PokemonEngine.model.zone.Zone;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private Map<String, Zone> loadedZones;
  private Zone currentZone;

  public ZoneController() {
    this.loadedZones = new HashMap<>();

    // Load first zone
    this.currentZone = loadZone("/zones/test.zo");
    //this.currentZone = new Zone("test", 800, 600, "/sprites/emerald.png", 16);
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

  private Zone loadZone(String path) {
    Zone zone = Game.gson.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(path))), Zone.class);
    zone.init();
    return zone;
  }
}
