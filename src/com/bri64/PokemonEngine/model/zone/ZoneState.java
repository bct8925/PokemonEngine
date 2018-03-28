package com.bri64.PokemonEngine.model.zone;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.behavior.PortalInteract;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.json.Entity_JSON;
import com.bri64.PokemonEngine.model.json.ZoneState_JSON;
import java.util.ArrayList;
import java.util.List;

public class ZoneState implements Gerializable {

  private String ID;
  private List<Entity> entities;

  public List<Entity> getEntities() {
    return entities;
  }

  private transient List<Entity> portals;

  public List<Entity> getPortals() {
    return portals;
  }

  public ZoneState(ZoneState_JSON json) {
    this.ID = json.getID();

    this.entities = new ArrayList<>();
    for (Entity_JSON e : json.getEntities()) {
      entities.add(new Entity(e));
    }

    init();
  }

  // Test constructor
  public ZoneState(String ID) {
    this.ID = ID;
    this.entities = new ArrayList<>();

    init();
  }

  @Override
  public void init() {
    this.portals = new ArrayList<>();
    for (Entity e : entities) {
      Interaction step = e.getStep();
      if (step != null && step instanceof PortalInteract) {
        portals.add(e);
      }
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
