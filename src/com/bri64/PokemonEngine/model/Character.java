package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Interaction;

public abstract class Character extends Entity {

  protected Direction dir;

  public Character(Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);

    this.dir = Direction.DOWN;
  }

  public void move(Direction d) {

  }
}

enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT
}
