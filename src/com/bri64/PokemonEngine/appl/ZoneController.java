package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.Zone;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private Gson gson;

  private Map<String, Zone> loadedZones;
  private Zone currentZone;

  public ZoneController(final Gson gson) {
    this.gson = gson;

    this.loadedZones = new HashMap<>();

    // Load first zone
    //this.currentZone = loadZone("/zones/test.zone");
    this.currentZone = new Zone(800, 600, "/sprites/emerald.png", 16);
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
    Zone zone = gson.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(path))), Zone.class);
    zone.init();
    return zone;
  }
}
