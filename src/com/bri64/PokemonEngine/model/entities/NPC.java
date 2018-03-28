package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.json.NPC_JSON;
import com.bri64.PokemonEngine.model.sprite.Sprite;

public class NPC extends Character {

  public NPC(NPC_JSON json, final ZoneController zoneController) {
    super(json, zoneController);

    this.type = "npc";
  }

  public NPC(int X, int Y, Sprite sprite, Interaction interactBehavior, final ZoneController zoneController) {
    super(X, Y, sprite, interactBehavior, zoneController);

    this.type = "npc";
  }
}
