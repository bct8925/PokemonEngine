package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.json.DynamicSprite_JSON;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DynamicSprite extends Sprite {

  private int nFrame;

  private transient int frame;

  public DynamicSprite(DynamicSprite_JSON json) {
    super(json);

    this.type = "dynamic";

    this.nFrame = json.getNFrame();
    this.frame = 0;
  }

  // Test constructor
  public DynamicSprite(int X, int Y, SpriteSheet SHEET, int nFrame) {
    super(X, Y, SHEET);

    this.type = "dynamic";

    this.nFrame = nFrame;
    this.frame = 0;
  }

  @Override
  public Image render() {
    update();

    Canvas temp = new Canvas(current.getCurrent().getSpriteData().getWidth(), current.getCurrent().getSpriteData().getHeight());
    GraphicsContext gc = temp.getGraphicsContext2D();

    gc.drawImage(current.getCurrent().render(), 0, 0);

    SnapshotParameters sp = new SnapshotParameters();
    sp.setFill(new Color(0, 0, 0, 0));
    return temp.snapshot(sp, null);
  }

  private void update() {
    frame++;
    if (frame == nFrame) {
      frame = 0;

      // Cycle sprite
      int index = getCurSprite();
      if (index + 1 == current.getSize()) {
        index = -1;
      }
      changeSprite(index + 1);
    }
  }

}
