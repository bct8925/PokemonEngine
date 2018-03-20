package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Behavior;

public abstract class Character extends Entity {

  protected Direction dir;

  public Character(Behavior interactBehavior,
      Behavior stepBehavior) {
    super(interactBehavior, stepBehavior);

    this.dir = Direction.DOWN;
  }
}

enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT
}
