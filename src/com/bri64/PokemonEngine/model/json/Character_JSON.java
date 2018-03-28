package com.bri64.PokemonEngine.model.json;

import com.bri64.PokemonEngine.model.entities.Direction;

public class Character_JSON extends Entity_JSON {
  protected Direction dir;

  public Direction getDir() {
    return dir;
  }

  public Character_JSON() {}
}
