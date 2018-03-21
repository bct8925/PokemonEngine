package bri64.PokemonEngine.model;

import javafx.scene.image.Image;

public class Sprite extends Renderable {

  private boolean animated;
  public boolean isAnimated() {
    return animated;
  }

  private Image image;

  public Sprite(String path) {
    this.image = new Image(this.getClass().getResourceAsStream(path));
  }

  @Override
  public Image render() {
    return image;
  }
}
