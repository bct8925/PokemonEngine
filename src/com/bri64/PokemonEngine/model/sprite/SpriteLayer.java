package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.RenderLayer;
import com.bri64.PokemonEngine.model.Renderable;
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
  private transient Image staticImage;
  private List<SpriteData> staticLayer;
  private List<Sprite> dynamicLayer;

  public SpriteLayer(int WIDTH, int HEIGHT, String PATH, List<SpriteData> STATIC, List<Sprite> DYNAMIC, RenderLayer LAYER) {
    this.width = WIDTH;
    this.height = HEIGHT;
    this.layer = LAYER;
    this.path = PATH;
    this.staticLayer = STATIC;
    this.dynamicLayer = DYNAMIC;

    this.pos = new Point2D(0, 0);

    init();
  }

  @Override
  public void init() {
    Image raw = new Image(Sprite.class.getResourceAsStream(path));
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    for (SpriteData s : staticLayer) {
      draw(gc, s, raw);
    }

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    this.staticImage = temp.snapshot(sp, null);

    for (Sprite s : dynamicLayer) {
      s.init();
    }
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(width, height);
    GraphicsContext gc = temp.getGraphicsContext2D();

    gc.drawImage(staticImage, 0, 0);
    for (Sprite s : dynamicLayer) {
      drawSprite(gc, s);
    }

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }

  private void draw(GraphicsContext gc, SpriteData s, Image src) {
    gc.drawImage(src, s.getSrcX(), s.getSrcY(), s.getWidth(), s.getHeight(), s.getX(), s.getY(), s.getWidth(), s.getHeight());
  }
  private void drawSprite(GraphicsContext gc, Sprite s) {
    gc.drawImage(s.render(), s.getPos().getX(), s.getPos().getY());
  }
}
