package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Behavior;

public class Player extends Character {

  public Player(Behavior interactBehavior,
      Behavior stepBehavior) {
    super(interactBehavior, stepBehavior);
  }
}
