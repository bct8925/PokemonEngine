package com.bri64.PokemonEngine.model;

import javafx.geometry.Point3D;
import javafx.scene.image.WritableImage;

public abstract class DrawableObject {
  protected Point3D pos;

  public Point3D getPos() {
    return pos.multiply(1);
  }
  public void move(final double x, final double y) {
    pos = pos.add(x, y, 0);
  }
  public void setLayer(final double layer) {
    pos = new Point3D(pos.getX(), pos.getY(), layer);
  }

  abstract public WritableImage getSprite();
  abstract public void render();
}
