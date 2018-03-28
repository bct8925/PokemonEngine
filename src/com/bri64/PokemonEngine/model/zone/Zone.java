package com.bri64.PokemonEngine.model.zone;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.Renderable;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.json.ZoneState_JSON;
import com.bri64.PokemonEngine.model.json.Zone_JSON;
import com.bri64.PokemonEngine.model.sprite.DynamicSprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteLayer;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import com.google.gson.Gson;
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
  private int tileSize;
  private SpriteLayer background;
  private SpriteLayer foreground;

  public String getID() {
    return ID;
  }
  public ZoneState getState() {
    return zoneState;
  }

  private transient ZoneController zoneController;
  private transient ZoneState zoneState;
  private transient Gson gsonIn;

  public Zone(Zone_JSON json, Gson gsonIn, final ZoneController zoneController) {
    this.pos = json.getPos();
    this.ID = json.getID();
    this.width = json.getWidth();
    this.height = json.getHeight();
    this.tileSize = json.getTileSize();

    this.background = new SpriteLayer(json.getBackground());
    this.foreground = new SpriteLayer(json.getForeground());

    this.gsonIn = gsonIn;
    this.zoneController = zoneController;
    init();
  }

  // Test constructor
  public Zone(String ID, int WIDTH, int HEIGHT, int TILE_SIZE, final ZoneController zoneController) {
    this.ID = ID;
    this.width = WIDTH;
    this.height = HEIGHT;
    this.tileSize = TILE_SIZE;

    List<SpriteData> s_test = new ArrayList<>();
    for (int i = 0; i < width / 16; i++) {
      for (int j = 0; j < height / 16; j++) {
        SpriteData s = new SpriteData(i * 16, j * 16, 16, 0, 16, 16);
        s_test.add(s);
      }
    }

    String path = "/sprites/emerald.png";
    List<DynamicSprite> d_test2 = new ArrayList<>();
    List<SpriteData> test2 = new ArrayList<>();
    test2.add(new SpriteData(48, 0, 16, 16));
    test2.add(new SpriteData(64, 0, 16, 16));
    d_test2.add(new DynamicSprite(64, 64, new SpriteSheet(path, test2), 60));

    this.background = new SpriteLayer(width, height, path, s_test, d_test2);
    this.foreground = new SpriteLayer(width, height, path, new ArrayList<>(), d_test2);

    this.pos = new Point2D(0, 0);

    this.zoneState = new ZoneState(ID);
    this.zoneController = zoneController;
  }

  @Override
  public void init() {
    this.zoneState = loadState();
  }

  private ZoneState loadState() {
    String PATH = "/zones/" + ID + ".zs";
    ZoneState_JSON zoneState = gsonIn.fromJson(new JsonReader(new InputStreamReader(this.getClass().getResourceAsStream(PATH))), ZoneState_JSON.class);
    return new ZoneState(zoneState);
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

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }

  public Image renderFG() {
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    draw(gc, foreground);

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }
}
