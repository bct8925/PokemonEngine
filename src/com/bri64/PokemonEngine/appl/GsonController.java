package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.behavior.ItemInteract;
import com.bri64.PokemonEngine.model.behavior.MessageInteract;
import com.bri64.PokemonEngine.model.behavior.PortalInteract;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.entities.character.Character;
import com.bri64.PokemonEngine.model.entities.character.NPC;
import com.bri64.PokemonEngine.model.sprite.DynamicSprite;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gson.RuntimeTypeAdapterFactory;

public class GsonController {

  private Gson input;
  private Gson output;

  public Gson getInput() {
    return input;
  }
  public Gson getOutput() {
    return output;
  }

  public GsonController() {
    GsonBuilder gb = new GsonBuilder();

    RuntimeTypeAdapterFactory<Sprite> sprites = RuntimeTypeAdapterFactory
        .of(Sprite.class, "type")
        .registerSubtype(Sprite.class, "sprite")
        .registerSubtype(DynamicSprite.class, "dynamic");
    RuntimeTypeAdapterFactory<Interaction> interactions = RuntimeTypeAdapterFactory
        .of(Interaction.class, "type")
        .registerSubtype(MessageInteract.class, "message")
        .registerSubtype(ItemInteract.class, "item")
        .registerSubtype(PortalInteract.class, "portal");
    RuntimeTypeAdapterFactory<Entity> entities = RuntimeTypeAdapterFactory
        .of(Entity.class, "type")
        .registerSubtype(Entity.class, "entity")
        .registerSubtype(Character.class, "character")
        .registerSubtype(NPC.class, "npc");

    gb.registerTypeAdapterFactory(sprites);
    gb.registerTypeAdapterFactory(interactions);
    gb.registerTypeAdapterFactory(entities);
    this.input = gb.create();
    this.output = new Gson();
  }
}
