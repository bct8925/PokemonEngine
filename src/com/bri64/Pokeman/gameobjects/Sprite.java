package com.bri64.Pokeman.gameobjects;

import java.awt.image.BufferedImage;

public class Sprite {

  private int width;
  private int height;

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  private BufferedImage curImage;
  private int curIndex;

  public BufferedImage getCurImage() {
    return curImage;
  }

  public int getCurIndex() {
    return curIndex;
  }

  private BufferedImage[] sprites;

  public void changeSprite(int index) {
    curImage = sprites[index];
    curIndex = index;
  }

  public Sprite(BufferedImage image, int num, int width) {
    this.width = width;
    this.height = image.getHeight();

    sprites = new BufferedImage[num];
    for (int i = 0; i < num; i++) {
      //System.out.println("Sprite " + i + ": " + (i * width));
      sprites[i] = image.getSubimage(i * width, 0, width, image.getHeight());
    }
    changeSprite(0);
  }


}
