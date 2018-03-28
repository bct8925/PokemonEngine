package com.bri64.PokemonEngine.model.json;

import com.bri64.PokemonEngine.model.sprite.SpriteData;
import java.util.List;

@SuppressWarnings("unused")
public class SpriteLayer_JSON extends Renderable_JSON {
  private int width;
  private int height;
  private String path;
  private List<SpriteData> staticLayer;
  private List<DynamicSprite_JSON> dynamicLayer;

  public int getWidth() {
    return width;
  }
  public int getHeight() {
    return height;
  }
  public String getPath() {
    return path;
  }
  public List<SpriteData> getStaticLayer() {
    return staticLayer;
  }
  public List<DynamicSprite_JSON> getDynamicLayer() {
    return dynamicLayer;
  }

  public SpriteLayer_JSON() {}
}
