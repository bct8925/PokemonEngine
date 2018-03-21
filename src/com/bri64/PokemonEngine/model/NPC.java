package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Interaction;

public class NPC extends Character {

  public NPC(Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);
  }
}
