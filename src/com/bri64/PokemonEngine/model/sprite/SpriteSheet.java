package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet implements Gerializable {

  private String path;
  private List<SpriteData> sheetData;

  private transient List<SingleSprite> sheet;
  private transient SingleSprite current;

  public SingleSprite getCurrent() {
    return current;
  }
  public void setCurrent(int index) {
    this.current = sheet.get(index);
  }

  public SpriteSheet(String PATH, SpriteData DATA) {
    this.path = PATH;
    this.sheetData = new ArrayList<>();
    sheetData.add(DATA);

    init();
  }

  public SpriteSheet(String PATH, List<SpriteData> SHEET) {
    this.path = PATH;
    this.sheetData = SHEET;

    init();
  }

  @Override
  public void init() {
    this.sheet = new ArrayList<>();
    for (SpriteData d : sheetData) {
      sheet.add(new SingleSprite(path, d));
    }
    this.current = sheet.get(0);
  }
}
