package com.bri64.PokemonEngine.model.json;

import java.util.List;

@SuppressWarnings("unused")
public class ZoneState_JSON {
  private String ID;
  private List<Entity_JSON> entities;

  public String getID() {
    return ID;
  }
  public List<Entity_JSON> getEntities() {
    return entities;
  }

  public ZoneState_JSON() {}
}
