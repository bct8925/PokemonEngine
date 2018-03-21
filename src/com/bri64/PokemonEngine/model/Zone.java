package com.bri64.PokemonEngine.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Zone extends Renderable {

  private int WIDTH;
  private int HEIGHT;

  private SpriteLayer background;
  private SpriteLayer foreground;
  private List<Entity> entities;

  public Zone(int width, int height) {
    this.WIDTH = width;
    this.HEIGHT = height;

    this.background = new SpriteLayer(800, 600, RenderLayer.BG);
    this.foreground = new SpriteLayer(800, 600, RenderLayer.FG);
    this.entities = new ArrayList<>();
    this.layer = RenderLayer.BG;

    this.pos = new Point2D(0, 0);
  }

  public void addEntity(Entity e) {
    entities.add(e);
  }

  private void draw(GraphicsContext gc, Renderable r) {
    gc.drawImage(r.render(), r.pos.getX(), r.pos.getY());
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(WIDTH, HEIGHT);
    GraphicsContext gc = temp.getGraphicsContext2D();

    draw(gc, background);
    for (Entity r : entities) {
      draw(gc, r);
    }
    draw(gc, foreground);

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }
}
