package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.sprite.Sprite;

public class NPC extends Character {

  public NPC(int X, int Y, Sprite sprite, final ZoneController zoneController, Interaction interactBehavior,
      Interaction stepBehavior) {
    super(X, Y, sprite, zoneController, interactBehavior, stepBehavior);

  }
}
