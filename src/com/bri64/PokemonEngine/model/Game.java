package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;

public class Game extends Renderable {

  // Controllers
  private RenderController renderController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController) {
    this.renderController = renderController;
    this.zoneController = new ZoneController();

    this.player = new Player(null, null);
  }

  // Render
  public void render() {
    // Clear screen
    renderController.clear();

    // Render zone (and its children)
    //zoneController.getCurrentZone().render();
  }

}
