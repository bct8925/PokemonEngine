package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.json.SpriteSheet_JSON;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheet implements Gerializable {

  private String path;
  private List<SpriteData> sheetData;

  private transient List<StaticSprite> sheet;
  private transient StaticSprite current;

  public StaticSprite getCurrent() {
    return current;
  }
  public int getCurrentIndex() {
    return sheet.indexOf(current);
  }
  public int getSize() {
    return sheet.size();
  }
  public void setCurrent(int index) {
    this.current = sheet.get(index);
  }

  public SpriteSheet(SpriteSheet_JSON json) {
    this.path = json.getPath();
    this.sheetData = json.getSheetData();

    init();
  }

  // Test constructors
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
      sheet.add(new StaticSprite(path, d));
    }
    this.current = sheet.get(0);
  }
}
