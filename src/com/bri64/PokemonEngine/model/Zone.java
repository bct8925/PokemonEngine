package com.bri64.PokemonEngine.model;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Zone extends Renderable implements Gerializable {

  private int width;
  private int height;
  private String path;
  private int tileSize;

  private MultiLayer background;
  private MultiLayer foreground;
  private List<Entity> entities;

  public Zone(int WIDTH, int HEIGHT, String PATH, int TILE_SIZE) {
    this.width = WIDTH;
    this.height = HEIGHT;
    this.path = PATH;
    this.tileSize = TILE_SIZE;

    List<SpriteData> s_test = new ArrayList<>();
    for (int i = 0; i < width / 16; i++) {
      for (int j = 0; j < height / 16; j++) {
        SpriteData s = new SpriteData(i * 16, j * 16, 16, 0, 16, 16);
        s_test.add(s);
      }
    }

    List<Sprite> d_test1 = new ArrayList<>();
    d_test1.add(new Sprite("/sprites/emerald.png", new SpriteData(208, 240, 48, 0, 16, 16)));
    List<Sprite> d_test2 = new ArrayList<>();
    d_test2.add(new Sprite("/sprites/emerald.png", new SpriteData(240, 240, 48, 0, 16, 16)));

    this.background = new MultiLayer(width, height, path, s_test, d_test1, RenderLayer.BG);
    this.foreground = new MultiLayer(width, height, path, new ArrayList<>(), d_test2, RenderLayer.FG);
    this.entities = new ArrayList<>();
    this.layer = RenderLayer.BG;

    this.pos = new Point2D(0, 0);

    init();
  }

  @Override
  public void init() {
    background.init();
    foreground.init();
  }

  public void addEntity(Entity e) {
    entities.add(e);
  }

  public Entity getEntityAt(int tileX, int tileY) {
    for (Entity e : entities) {
      if (tileX * tileSize == e.pos.getX() && tileY * tileSize == e.pos.getY()) {
        return e;
      }
    }
    return null;
  }

  private void draw(GraphicsContext gc, Renderable r) {
    gc.drawImage(r.render(), r.pos.getX(), r.pos.getY());
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(width, height);
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
