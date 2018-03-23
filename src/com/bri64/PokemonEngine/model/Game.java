package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.behavior.ItemInteract;
import com.bri64.PokemonEngine.model.behavior.MessageInteract;
import com.bri64.PokemonEngine.model.behavior.PortalInteract;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.entities.Player;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import com.bri64.PokemonEngine.model.zone.Zone;
import com.bri64.PokemonEngine.model.zone.ZoneState;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.RuntimeTypeAdapterFactory;
import java.util.ArrayList;
import java.util.List;

public class Game {

  // Dependencies
  public static Gson gson;

  // Controllers
  private RenderController renderController;
  private InputController inputController;
  private ZoneController zoneController;

  // Objects
  private Player player;

  // Init
  public Game(final RenderController renderController, final InputController inputController) {
    // Setup dependencies
    RuntimeTypeAdapterFactory<Interaction> typeFactory = RuntimeTypeAdapterFactory
        .of(Interaction.class, "type")
        .registerSubtype(MessageInteract.class, "message")
        .registerSubtype(ItemInteract.class, "item")
        .registerSubtype(PortalInteract.class, "portal");
    gson = new GsonBuilder().registerTypeAdapterFactory(typeFactory).create();

    // Setup controllers
    this.renderController = renderController;
    this.inputController = inputController;
    this.zoneController = new ZoneController();

    // Setup game
    List<SpriteData> playerSprites = new ArrayList<>();
    playerSprites.add(new SpriteData(0, 0, 16, 20));
    playerSprites.add(new SpriteData(16, 0, 16, 20));
    playerSprites.add(new SpriteData(32, 0, 16, 20));
    playerSprites.add(new SpriteData(48, 0, 16, 20));
    this.player = new Player(0, 0, new Sprite(Player.SPRITE_SRC, new SpriteSheet(Player.SPRITE_SRC, playerSprites)), inputController, renderController, zoneController, null, null);

    Entity sign = new Entity(2, 2, new Sprite("/sprites/emerald.png", new SpriteSheet("/sprites/emerald.png", new SpriteData(48, 0, 16, 16))), new MessageInteract("Sign clicked"), null);
    zoneController.getCurrentZone().addEntity(sign);

    Gson gson = new Gson();
    System.out.println(gson.toJson(zoneController.getCurrentZone(), Zone.class));
    System.out.println(gson.toJson(zoneController.getCurrentZone().getState(), ZoneState.class));
  }

  // Render
  public void render() {
    // Clear screen
    renderController.clear();

    // Render zone (and its children)
    renderController.render(zoneController.getCurrentZone());

    renderController.renderPlayer(player);

    // Render GUI elements
  }

}
