package com.bri64.PokemonEngine.model;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite extends Renderable implements Gerializable {

  private String path;
  private SpriteData spriteData;

  private transient Image image;

  public Sprite(String PATH, SpriteData SPRITE_DATA) {
    this.path = PATH;
    this.spriteData = SPRITE_DATA;

    this.setPos(spriteData.getX(), spriteData.getY());

    init();
  }

  @Override
  public void init() {
    Image raw = new Image(Sprite.class.getResourceAsStream(path));
    Canvas temp = new Canvas(spriteData.getWidth(), spriteData.getHeight());
    GraphicsContext gc = temp.getGraphicsContext2D();

    // Get sub-image
    gc.drawImage(raw, spriteData.getSrcX(), spriteData.getSrcY(), spriteData.getWidth(), spriteData.getHeight(), 0, 0, spriteData.getWidth(), spriteData.getHeight());

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    this.image = temp.snapshot(sp, null);
  }

  @Override
  public Image render() {
    return image;
  }
}
