package com.bri64.PokemonEngine.model.zone;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.entities.Entity;
import java.util.ArrayList;
import java.util.List;

public class ZoneState implements Gerializable {

  private String ID;
  private int tileSize;

  private List<Entity> entities;

  public List<Entity> getEntities() {
    return entities;
  }

  public ZoneState(String ID, int TILE_SIZE) {
    this.ID = ID;
    this.tileSize = TILE_SIZE;
    this.entities = new ArrayList<>();

    init();
  }

  @Override
  public void init() {
    for (Entity e : entities) {
      e.init();
    }
  }

  public void addEntity(Entity e) {
    entities.add(e);
  }

  public Entity getEntityAt(double col, double row) {
    for (Entity e : entities) {
      if (col == e.getPos().getX() && row == e.getPos().getY()) {
        return e;
      }
    }
    return null;
  }
}
