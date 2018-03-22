package com.bri64.PokemonEngine.model.zone;

import com.bri64.PokemonEngine.model.Game;
import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.RenderLayer;
import com.bri64.PokemonEngine.model.Renderable;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.sprite.DynamicSprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteLayer;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Zone extends Renderable implements Gerializable {

  private String ID;
  private int width;
  private int height;
  private String path;
  private int tileSize;

  public String getID() {
    return ID;
  }

  private SpriteLayer background;
  private SpriteLayer foreground;
  private transient ZoneState zoneState;

  public ZoneState getState() {
    return zoneState;
  }

  public Zone(String ID, int WIDTH, int HEIGHT, String PATH, int TILE_SIZE) {
    this.ID = ID;
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

    List<DynamicSprite> d_test2 = new ArrayList<>();
    d_test2.add(new DynamicSprite(240, 240, path, new SpriteSheet(path, new SpriteData(48, 0, 16, 16))));

    this.background = new SpriteLayer(width, height, path, s_test, new ArrayList<>(), RenderLayer.BG);
    this.foreground = new SpriteLayer(width, height, path, new ArrayList<>(), d_test2, RenderLayer.FG);
    this.zoneState = new ZoneState(ID, tileSize);
    this.layer = RenderLayer.BG;

    this.pos = new Point2D(0, 0);

    //init();
  }

  @Override
  public void init() {
    loadState();

    background.init();
    foreground.init();
  }

  private void loadState() {
    String PATH = "/zones/" + ID + ".zs";
    this.zoneState = Game.gson.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(PATH))), ZoneState.class);
    zoneState.init();
  }

  // ZoneState proxy
  public void addEntity(Entity e) {
    zoneState.addEntity(e);
  }

  public Entity getEntityAt(double col, double row) {
    return zoneState.getEntityAt(col, row);
  }


  // Rendering
  private void draw(GraphicsContext gc, SpriteLayer s) {
    gc.drawImage(s.render(), s.getPos().getX(), s.getPos().getY());
  }
  private void drawEntity(GraphicsContext gc, Entity e) {
    gc.drawImage(e.render(), e.getPos().getX() * tileSize, e.getPos().getY() * tileSize);
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    draw(gc, background);
    for (Entity e : zoneState.getEntities()) {
      drawEntity(gc, e);
    }
    draw(gc, foreground);

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }
}
