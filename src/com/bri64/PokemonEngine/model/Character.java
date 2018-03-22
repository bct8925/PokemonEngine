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
    switch (d) {
      case UP:
        movePos(0, -16);
        break;
      case DOWN:
        movePos(0, 16);
        break;
      case LEFT:
        movePos(-16, 0);
        break;
      case RIGHT:
        movePos(16, 0);
        break;
    }
  }
}

enum Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT
}
