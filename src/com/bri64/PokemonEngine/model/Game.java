package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import javafx.animation.AnimationTimer;

public class Game {

  // Controllers
  private RenderController renderController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController) {
    this.renderController = renderController;
    this.player = new Player(renderController);
    this.zoneController = new ZoneController(renderController);

    new AnimationTimer() {
      @Override
      public void handle(long now) {
        render();
      }
    }.start();
  }

  // Render
  private void render() {
    // Clear screen
    renderController.clear();

    // Render objects (layers are hard)
    zoneController.getCurrentZone().render();
    player.render();
  }

}
