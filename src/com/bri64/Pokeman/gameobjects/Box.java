package com.bri64.Pokeman.gameobjects;

import java.awt.Rectangle;

@SuppressWarnings({"WeakerAccess", "unused"})
public class Box {

  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected boolean exists;

  public boolean exists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Rectangle getRectangle() {
    return new Rectangle(x, y, width, height);
  }

  public Rectangle getLeftRectangle() {
    return new Rectangle(x, y, width / 2, height);
  }

  public Rectangle getRightRectangle() {
    return new Rectangle(x + (width / 2), y, width / 2, height);
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void addX(int x) {
    this.x += x;
  }

  public void addY(int y) {
    this.y += y;
  }

  public void centerX(int x) {
    this.x = (x - (width / 2));
  }

  public void centerY(int y) {
    this.y = (y - (height / 2));
  }

  public void centerBox(int x, int y) {
    this.x = (x - (width / 2));
    this.y = (y - (height / 2));
  }

  public Box(int width, int height) {
    exists = true;
    this.width = width;
    this.height = height;
  }

}
