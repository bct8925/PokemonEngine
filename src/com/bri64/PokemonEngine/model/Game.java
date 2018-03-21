package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.google.gson.Gson;

public class Game {

  // Dependencies
  private Gson gson;

  // Controllers
  private RenderController renderController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController) {
    // Setup dependencies
    this.gson = new Gson();

    // Setup controllers
    this.renderController = renderController;
    this.zoneController = new ZoneController(gson);

    // Setup game
    this.player = new Player(null, null);
    zoneController.getCurrentZone().addEntity(player);
  }

  // Render
  public void render() {
    // Clear screen
    renderController.clear();

    // Render zone (and its children)
    renderController.render(zoneController.getCurrentZone());

    // Render GUI elements
  }

}
