package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.json.Character_JSON;
import com.bri64.PokemonEngine.model.sprite.Sprite;

public abstract class Character extends Entity {

  protected Direction dir;

  protected transient ZoneController zoneController;

  public Character(Character_JSON json, final ZoneController zoneController) {
    super(json);

    this.type = "character";

    this.dir = json.getDir();
    sprite.changeSprite(dir.ordinal());

    this.zoneController = zoneController;
  }

  public Character(int X, int Y, Sprite sprite, Interaction interactBehavior, final ZoneController zoneController) {
    super(X, Y, sprite, interactBehavior, null);

    this.type = "character";

    this.dir = Direction.DOWN;
    sprite.changeSprite(dir.ordinal());

    this.zoneController = zoneController;
  }

  public boolean move(Direction d) {
    this.dir = d;
    sprite.changeSprite(dir.ordinal());

    switch (dir) {
      case UP:
        if (zoneController.getEntityAt(pos.getX(), pos.getY() - 1) == null) {
          movePos(0, -1);
        } else {
          return false;
        }
        break;
      case DOWN:
        if (zoneController.getEntityAt(pos.getX(), pos.getY() + 1) == null) {
          movePos(0, 1);
        } else {
          return false;
        }
        break;
      case LEFT:
        if (zoneController.getEntityAt(pos.getX() - 1, pos.getY()) == null) {
          movePos(-1, 0);
        } else {
          return false;
        }
        break;
      case RIGHT:
        if (zoneController.getEntityAt(pos.getX() + 1, pos.getY()) == null) {
          movePos(1, 0);
        } else {
          return false;
        }
        break;
    }
    return true;
  }
}

