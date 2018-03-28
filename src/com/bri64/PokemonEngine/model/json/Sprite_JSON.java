package com.bri64.PokemonEngine.model.json;

import java.util.List;

@SuppressWarnings("unused")
public class Sprite_JSON extends Renderable_JSON {
  private List<SpriteSheet_JSON> spriteSheets;

  public List<SpriteSheet_JSON> getSpriteSheets() {
    return spriteSheets;
  }

  public Sprite_JSON() {}
}
