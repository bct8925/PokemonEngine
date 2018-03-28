package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.json.Zone_JSON;
import com.bri64.PokemonEngine.model.zone.Zone;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private Map<String, Zone> loadedZones;
  private Zone currentZone;

  public Zone getZone(final String ID) {
    return loadedZones.get(ID);
  }
  public Zone getCurrentZone() {
    return currentZone;
  }
  public void setCurrentZone(final String ID) {
    currentZone = getZone(ID);
  }

  private transient Gson gsonIn;

  public ZoneController(Gson gsonIn) {
    this.gsonIn = gsonIn;

    // Load first zone
    this.loadedZones = new HashMap<>();
    this.currentZone = loadZone("/zones/test.zo");
    //this.currentZone = new Zone("test", 800, 600, 16, this);
  }

  private Zone loadZone(String path) {
    Zone_JSON zone = gsonIn.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(path))), Zone_JSON.class);
    return new Zone(zone, gsonIn, this);
  }

  // Zone proxy
  public Entity getEntityAt(double col, double row) {
    return currentZone.getEntityAt(col, row);
  }
}
