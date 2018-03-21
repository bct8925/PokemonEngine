package com.bri64.PokemonEngine.model;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite extends Renderable {

  private boolean animated;
  public boolean isAnimated() {
    return animated;
  }

  private transient Image image;

  public Sprite(String path, int x, int y, int width, int height) {
    Image raw = new Image(Sprite.class.getResourceAsStream(path));
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    // Get sub-image
    gc.drawImage(raw, x, y, width, height, 0, 0, width, height);

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    this.image = temp.snapshot(sp, null);
  }

  @Override
  public Image render() {
    return image;
  }
}
