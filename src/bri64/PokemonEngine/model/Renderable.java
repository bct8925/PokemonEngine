package bri64.PokemonEngine.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Renderable {

  protected Point2D pos;
  protected RenderLayer layer;

  public Point2D getPos() {
    return pos;
  }
  public void setPos(double x, double y) {
    pos = new Point2D(x, y);
  }
  protected void move(final double x, final double y) {
    pos = pos.add(x, y);
  }

  abstract public Image render();
}

enum RenderLayer {
  BG,
  OBJ,
  FG
}
