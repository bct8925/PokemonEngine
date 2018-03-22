package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.Renderable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite extends Renderable implements Gerializable {

  private String path;
  private List<SpriteSheet> spriteSheets;

  private transient SpriteSheet current;

  public Sprite(String PATH, SpriteSheet SHEET) {
    this(0, 0, PATH, SHEET);
  }

  public Sprite(int X, int Y, String PATH, SpriteSheet SHEET) {
    this.path = PATH;
    this.spriteSheets = new ArrayList<>();
    spriteSheets.add(SHEET);
    this.current = spriteSheets.get(0);

    this.setPos(X, Y);

    init();
  }

  public Sprite(String PATH, List<SpriteSheet> SHEETS) {
    this(0, 0, PATH, SHEETS);
  }

  public Sprite(int X, int Y, String PATH, List<SpriteSheet> SHEETS) {
    this.path = PATH;
    this.spriteSheets = SHEETS;
    this.current = spriteSheets.get(0);

    this.setPos(X, Y);

    init();
  }

  @Override
  public void init() {
    for (SpriteSheet s : spriteSheets) {
      s.init();
    }
    this.current = spriteSheets.get(0);
  }

  public void changeSprite(int index) {
    current.setCurrent(index);
  }

  public void changeSheet(int index) {
    current = spriteSheets.get(index);
  }

  @Override
  public Image render() {
    Canvas temp = new Canvas(current.getCurrent().getSpriteData().getWidth(), current.getCurrent().getSpriteData().getHeight());
    GraphicsContext gc = temp.getGraphicsContext2D();

    gc.drawImage(current.getCurrent().render(), pos.getX(), pos.getY());

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }
}
