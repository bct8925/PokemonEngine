package com.bri64.PokemonEngine.model;

import javafx.geometry.Point2D;

public abstract class Renderable {
  protected Point2D pos;
  protected RenderLayer layer;

  public Point2D getPos() {
    return pos;
  }
  public void setPos(Point2D pos) {
    this.pos = pos;
  }
  public void move(final double x, final double y) {
    pos = pos.add(x, y);
  }

  abstract public void render();
}

enum RenderLayer {
  BG,
  OBJ,
  FG
}
