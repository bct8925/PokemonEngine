package com.bri64.PokemonEngine.model.json;

import com.bri64.PokemonEngine.model.behavior.Interaction;

public class Entity_JSON extends Renderable_JSON {
  protected Sprite_JSON sprite;
  protected Interaction interactBehavior;
  protected Interaction stepBehavior;

  public Sprite_JSON getSprite() {
    return sprite;
  }
  public Interaction getInteractBehavior() {
    return interactBehavior;
  }
  public Interaction getStepBehavior() {
    return stepBehavior;
  }

  public Entity_JSON() {}
}
