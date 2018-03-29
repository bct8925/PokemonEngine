package com.bri64.PokemonEngine.model.entities.character;

public class MovingState extends CharacterState {

  public MovingState(Character character) {
    super(character);
  }

  @Override
  public boolean canMove(Direction d) {
    return false;
  }

  @Override
  public void move(Direction d) {}

  @Override
  public void update() {
    instance.updateSprite();
  }
}
