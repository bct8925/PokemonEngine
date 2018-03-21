package bri64.PokemonEngine.model;

import bri64.PokemonEngine.model.behavior.Interaction;

public abstract class Character extends Entity {

  protected Direction dir;

  public Character(Interaction interactBehavior,
      Interaction stepBehavior) {
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
