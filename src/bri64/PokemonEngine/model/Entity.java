package bri64.PokemonEngine.model;

import bri64.PokemonEngine.model.behavior.Interaction;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

/**
 * Entity - a {@link Renderable} with {@link Interaction}(s)
 */
public class Entity extends Renderable {

  protected Sprite sprite;

  protected Interaction interactBehavior;
  protected Interaction stepBehavior;

  public Entity(Interaction interactBehavior, Interaction stepBehavior) {
    this.interactBehavior = interactBehavior;
    this.stepBehavior = stepBehavior;
    this.layer = RenderLayer.OBJ;

    this.pos = new Point2D(0, 0);
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