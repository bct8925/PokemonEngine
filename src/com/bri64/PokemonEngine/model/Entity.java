package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.model.behavior.Behavior;

public class Entity extends Renderable {

  protected Behavior interactBehavior;
  protected Behavior stepBehavior;

  public Entity(Behavior interactBehavior, Behavior stepBehavior) {
    this.interactBehavior = interactBehavior;
    this.stepBehavior = stepBehavior;
    this.layer = RenderLayer.OBJ;
  }

  public void render() {

  }
}
