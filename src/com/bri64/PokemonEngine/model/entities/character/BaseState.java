package com.bri64.PokemonEngine.model.entities.character;

public class BaseState extends CharacterState {

  public BaseState(Character character) {
    super(character);
  }

  @Override
  public boolean canMove(Direction d) {
    return instance.canMoveDir(d);
  }

  @Override
  public void move(Direction d) {
    instance.changeState(instance.getMovingState());
    instance.moveCharacter(d);
  }

  @Override
  public void update() {}
}
