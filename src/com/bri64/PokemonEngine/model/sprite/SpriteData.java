package com.bri64.PokemonEngine.model.sprite;

public class SpriteData {
  private int x;
  private int y;
  private int srcX;
  private int srcY;
  private int width;
  private int height;

  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public int getSrcX() {
    return srcX;
  }
  public int getSrcY() {
    return srcY;
  }
  public int getWidth() {
    return width;
  }
  public int getHeight() {
    return height;
  }

  public SpriteData(int SRC_X, int SRC_Y, int WIDTH, int HEIGHT) {
    this(0, 0, SRC_X, SRC_Y, WIDTH, HEIGHT);
  }

  public SpriteData(int X, int Y, int SRC_X, int SRC_Y, int WIDTH, int HEIGHT) {
    this.x = X;
    this.y = Y;
    this.srcX = SRC_X;
    this.srcY = SRC_Y;
    this.width = WIDTH;
    this.height = HEIGHT;
  }
}
