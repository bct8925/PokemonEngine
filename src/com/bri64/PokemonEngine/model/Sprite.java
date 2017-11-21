package com.bri64.PokemonEngine.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class Sprite {
  private List<WritableImage> frames;
  private int current;
  private int max;

  public Sprite(final String fileName, final int num, final int width) {
    this.max = num;
    this.current = 0;
    this.frames = new ArrayList<>();
    Image base = new Image(fileName);
    for (int i = 0; i < max; i++) {
      frames.add(new WritableImage(base.getPixelReader(), i * width, 0, width,
          (int) base.getHeight()));
    }
  }

  public int currentIndex() {
    return current;
  }
  public int getMax() {
    return max;
  }

  public void setCurrent(final int index) {
    current = index;
  }
  public WritableImage currentSprite() {
    return frames.get(current);
  }


}
