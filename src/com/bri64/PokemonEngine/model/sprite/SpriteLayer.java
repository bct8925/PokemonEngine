package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.Renderable;
import com.bri64.PokemonEngine.model.json.DynamicSprite_JSON;
import com.bri64.PokemonEngine.model.json.SpriteLayer_JSON;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class SpriteLayer extends Renderable implements Gerializable {

  private int width;
  private int height;
  private String path;
  private List<SpriteData> staticLayer;
  private List<DynamicSprite> dynamicLayer;

  private transient Image staticImage;

  public SpriteLayer(SpriteLayer_JSON json) {
    this.pos = json.getPos();
    this.width = json.getWidth();
    this.height = json.getHeight();
    this.path = json.getPath();
    this.staticLayer = json.getStaticLayer();

    this.dynamicLayer = new ArrayList<>();
    for (DynamicSprite_JSON j : json.getDynamicLayer()) {
      dynamicLayer.add(new DynamicSprite(j));
    }

    init();
  }

  // Test constructor
  public SpriteLayer(int WIDTH, int HEIGHT, String PATH, List<SpriteData> STATIC, List<DynamicSprite> DYNAMIC) {
    this.width = WIDTH;
    this.height = HEIGHT;
    this.path = PATH;
    this.staticLayer = STATIC;
    this.dynamicLayer = DYNAMIC;

    this.pos = new Point2D(0, 0);

    init();
  }

  @Override
  public void init() {
    this.staticImage = initStatic();
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    gc.drawImage(staticImage, 0, 0);
    for (DynamicSprite s : dynamicLayer) {
      drawSprite(gc, s);
    }

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }

  private Image initStatic() {
    Image raw = new Image(Sprite.class.getResourceAsStream(path));
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    for (SpriteData s : staticLayer) {
      draw(gc, s, raw);
    }

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }

  private void draw(GraphicsContext gc, SpriteData s, Image src) {
    gc.drawImage(src, s.getSrcX(), s.getSrcY(), s.getWidth(), s.getHeight(), s.getX(), s.getY(), s.getWidth(), s.getHeight());
  }
  private void drawSprite(GraphicsContext gc, DynamicSprite s) {
    gc.drawImage(s.render(), s.getPos().getX(), s.getPos().getY());
  }
}
