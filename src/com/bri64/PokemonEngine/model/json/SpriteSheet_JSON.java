package com.bri64.PokemonEngine.model.json;

import com.bri64.PokemonEngine.model.sprite.SpriteData;
import java.util.List;

@SuppressWarnings("unused")
public class SpriteSheet_JSON {
  private String path;
  private List<SpriteData> sheetData;

  public String getPath() {
    return path;
  }
  public List<SpriteData> getSheetData() {
    return sheetData;
  }

  public SpriteSheet_JSON() {}
}
