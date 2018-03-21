package bri64.PokemonEngine.model;

import bri64.PokemonEngine.model.behavior.Interaction;

public class Player extends Character {

  public Player(Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);

    this.sprite = new Sprite("/sprites/player.png");
  }
}
