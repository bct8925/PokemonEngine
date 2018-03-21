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

  private int WIDTH = 800;
  private int HEIGHT = 600;

  private SpriteLayer background;
  private SpriteLayer foreground;
  private List<Entity> entities;

  public Zone() {
    this.background = new SpriteLayer(RenderLayer.BG);
    this.foreground = new SpriteLayer(RenderLayer.FG);
    this.entities = new ArrayList<>();
    this.layer = RenderLayer.BG;

    this.pos = new Point2D(0, 0);
  }

  public Zone(String path) {


    this.background = new SpriteLayer(RenderLayer.BG);
    this.foreground = new SpriteLayer(RenderLayer.FG);
    this.entities = new ArrayList<>();
    this.layer = RenderLayer.BG;
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
