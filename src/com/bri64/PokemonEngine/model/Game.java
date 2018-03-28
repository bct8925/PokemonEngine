package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.GsonController;
import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.behavior.MessageInteract;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.entities.Player;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import com.bri64.PokemonEngine.model.zone.Zone;
import com.bri64.PokemonEngine.model.zone.ZoneState;
import java.util.ArrayList;
import java.util.List;

public class Game {

  // Controllers
  private GsonController gsonController;
  private RenderController renderController;
  private InputController inputController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController, final InputController inputController) {
    // Setup controllers
    this.gsonController = new GsonController();
    this.renderController = renderController;
    this.inputController = inputController;
    this.zoneController = new ZoneController(gsonController.getInput());

    // Setup game
    List<SpriteData> playerSprites = new ArrayList<>();
    playerSprites.add(new SpriteData(0, 0, 16, 20));
    playerSprites.add(new SpriteData(16, 0, 16, 20));
    playerSprites.add(new SpriteData(32, 0, 16, 20));
    playerSprites.add(new SpriteData(48, 0, 16, 20));
    this.player = new Player(0, 0, new Sprite(new SpriteSheet(Player.SPRITE_SRC, playerSprites)), renderController, inputController, zoneController);

    Entity sign = new Entity(2, 2, new Sprite(new SpriteSheet("/sprites/emerald.png", new SpriteData(48, 0, 16, 16))), new MessageInteract("Sign clicked"), null);
    zoneController.getCurrentZone().addEntity(sign);

    System.out.println(gsonController.getOutput().toJson(zoneController.getCurrentZone(), Zone.class));
    System.out.println(gsonController.getOutput().toJson(zoneController.getCurrentZone().getState(), ZoneState.class));
  }

  // Render
  public void render() {
    // Clear screen
    renderController.clear();

    // Render zone background & entities
    renderController.renderBG(zoneController.getCurrentZone());

    // Render player
    renderController.renderPlayer(player);

    // Render zone foreground
    renderController.renderFG(zoneController.getCurrentZone());

    // Render GUI elements
  }

}
