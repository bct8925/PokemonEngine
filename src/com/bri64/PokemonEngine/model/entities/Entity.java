package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.RenderLayer;
import com.bri64.PokemonEngine.model.Renderable;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Entity - a {@link Renderable} with {@link Interaction}(s)
 */
public class Entity extends Renderable implements Gerializable {

  protected Sprite sprite;

  protected Interaction interactBehavior;
  protected Interaction stepBehavior;

  public Entity(int X, int Y, Sprite sprite, Interaction interactBehavior, Interaction stepBehavior) {
    this.sprite = sprite;
    this.interactBehavior = interactBehavior;
    this.stepBehavior = stepBehavior;
    this.layer = RenderLayer.OBJ;

    this.pos = new Point2D(X, Y);

    init();
  }

  @Override
  public void init() {
    sprite.init();
  }

  public void interact() {
    if (interactBehavior != null) {
      interactBehavior.execute();
    }
  }

  public void step() {
    if (stepBehavior != null) {
      stepBehavior.execute();
    }
  }

  @Override
  public Image render() {
    return sprite.render();
  }
}
