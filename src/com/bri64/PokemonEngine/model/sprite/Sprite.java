package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.Renderable;
import com.bri64.PokemonEngine.model.json.SpriteSheet_JSON;
import com.bri64.PokemonEngine.model.json.Sprite_JSON;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Sprite extends Renderable implements Gerializable {

  protected String type;

  protected List<SpriteSheet> spriteSheets;

  protected transient SpriteSheet current;

  public Sprite(Sprite_JSON json) {
    this.type = "sprite";

    this.pos = json.getPos();

    this.spriteSheets = new ArrayList<>();
    for (SpriteSheet_JSON j : json.getSpriteSheets()) {
      this.spriteSheets.add(new SpriteSheet(j));
    }

    init();
  }

  // Test constructors
  public Sprite(SpriteSheet SHEET) {
    this(0, 0, SHEET);
  }
  public Sprite(int X, int Y, SpriteSheet SHEET) {
    this.type = "sprite";

    this.spriteSheets = new ArrayList<>();
    spriteSheets.add(SHEET);
    this.current = spriteSheets.get(0);

    this.setPos(X, Y);

    init();
  }
  public Sprite(List<SpriteSheet> SHEETS) {
    this(0, 0, SHEETS);
  }
  public Sprite(int X, int Y, List<SpriteSheet> SHEETS) {
    this.type = "sprite";

    this.spriteSheets = SHEETS;
    this.current = spriteSheets.get(0);

    this.setPos(X, Y);

    init();
  }

  @Override
  public void init() {
    this.current = spriteSheets.get(0);
  }

  public int getCurSprite() {
    return current.getCurrentIndex();
  }
  public int getCurSheet() {
    return spriteSheets.indexOf(current);
  }
  public void changeSprite(int index) {
    this.current.setCurrent(index);
  }
  public void changeSheet(int index) {
    this.current = spriteSheets.get(index);
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
