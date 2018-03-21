package com.bri64.PokemonEngine.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SpriteLayer extends Renderable {

  private int WIDTH = 800;
  private int HEIGHT = 600;

  private WritableImage staticLayer;
  private List<Sprite> dynamicLayer;

  public SpriteLayer(RenderLayer layer) {
    this.staticLayer = new WritableImage(WIDTH, HEIGHT);
    this.dynamicLayer = new ArrayList<>();
    this.layer = layer;

    this.pos = new Point2D(0, 0);
  }

  private void draw(GraphicsContext gc, Renderable r) {
    gc.drawImage(r.render(), r.pos.getX(), r.pos.getY());
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(WIDTH, HEIGHT);
    GraphicsContext gc = temp.getGraphicsContext2D();

    gc.drawImage(staticLayer, 0, 0);
    for (Sprite s : dynamicLayer) {
      draw(gc, s);
    }

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }
}
