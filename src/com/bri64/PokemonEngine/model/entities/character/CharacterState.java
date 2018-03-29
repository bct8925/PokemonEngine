package com.bri64.PokemonEngine.model.entities.character;

public abstract class CharacterState {
  protected Character instance;

  public CharacterState(Character character) {
    this.instance = character;
  }

  public abstract boolean canMove(Direction d);
  public abstract void move(Direction d);
  public abstract void update();
}
