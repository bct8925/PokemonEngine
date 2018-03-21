package bri64.PokemonEngine.model;

import bri64.PokemonEngine.model.behavior.Interaction;

public class NPC extends Character {

  public NPC(Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);
  }
}
