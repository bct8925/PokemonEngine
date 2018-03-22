package com.bri64.PokemonEngine.model.sprite;

import java.util.List;

public class DynamicSprite extends Sprite {

  public DynamicSprite(int X, int Y, String PATH, SpriteSheet SHEET) {
    super(PATH, SHEET);
    this.setPos(X, Y);
  }

  public DynamicSprite(int X, int Y, String PATH, List<SpriteSheet> SHEETS) {
    super(PATH, SHEETS);
    this.setPos(X, Y);
  }
}
