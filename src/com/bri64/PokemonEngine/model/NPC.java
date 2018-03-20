package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Behavior;

public class NPC extends Character {

  public NPC(Behavior interactBehavior,
      Behavior stepBehavior) {
    super(interactBehavior, stepBehavior);
  }
}
