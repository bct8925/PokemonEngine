package com.bri64.PokemonEngine.model.json;

@SuppressWarnings("unused")
public class Zone_JSON extends Renderable_JSON {
  private String ID;
  private int width;
  private int height;
  private int tileSize;

  private SpriteLayer_JSON background;
  private SpriteLayer_JSON foreground;

  public String getID() {
    return ID;
  }
  public int getWidth() {
    return width;
  }
  public int getHeight() {
    return height;
  }
  public int getTileSize() {
    return tileSize;
  }
  public SpriteLayer_JSON getBackground() {
    return background;
  }
  public SpriteLayer_JSON getForeground() {
    return foreground;
  }

  public Zone_JSON() {}
}
