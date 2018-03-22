package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.appl.input.InputController;
import com.google.gson.Gson;

public class Game {

  // Dependencies
  private Gson gson;

  // Controllers
  private RenderController renderController;
  private InputController inputController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController, final InputController inputController) {
    // Setup dependencies
    this.gson = new Gson();

    // Setup controllers
    this.renderController = renderController;
    this.inputController = inputController;
    this.zoneController = new ZoneController(gson);

    // Setup game
    this.player = new Player(inputController, 0, 0, null, null);
    zoneController.getCurrentZone().addEntity(player);

    Gson gson = new Gson();
    System.out.println(gson.toJson(zoneController.getCurrentZone(), Zone.class));
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
