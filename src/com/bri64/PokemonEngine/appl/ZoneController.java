package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.json.Zone_JSON;
import com.bri64.PokemonEngine.model.zone.Zone;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class ZoneController {

  private List<Zone> loadedZones;
  private Zone currentZone;

  public Zone getCurrentZone() {
    return currentZone;
  }

  private transient RenderController renderController;
  private transient Gson gsonIn;

  public ZoneController(final RenderController renderController, final Gson gsonIn) {
    this.renderController = renderController;
    this.gsonIn = gsonIn;

    // Load first zone
    this.loadedZones = new ArrayList<>();
    this.currentZone = loadZone("/zones/test.zo");
    //this.currentZone = new Zone("test", 800, 600, 16, this);
    loadedZones.add(currentZone);
  }

  private Zone loadZone(String path) {
    Zone_JSON zone = gsonIn.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(path))), Zone_JSON.class);
    return new Zone(zone, gsonIn, this);
  }

  // Zone proxy
  public Entity getEntityAt(double col, double row) {
    return currentZone.getEntityAt(col, row);
  }

  public void renderLoadedZonesBG() {
    for (Zone z : loadedZones) {
      renderController.renderBG(z);
    }
  }
  public void renderLoadedZonesFG() {
    for (Zone z : loadedZones) {
      renderController.renderFG(z);
    }
  }
}
