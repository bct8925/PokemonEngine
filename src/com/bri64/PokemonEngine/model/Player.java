package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Interaction;

public class Player extends Character {

  public Player(Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);

    this.sprite = new Sprite("/sprites/player.png", 16, 0, 16, 20);
  }
}
