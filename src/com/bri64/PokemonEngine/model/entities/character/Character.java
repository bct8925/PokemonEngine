package com.bri64.PokemonEngine.model.entities.character;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.json.Character_JSON;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public abstract class Character extends Entity {

  protected Direction dir;

  protected transient int frame;
  protected transient boolean left;

  protected transient CharacterState currentState;
  protected transient BaseState baseState;
  protected transient MovingState movingState;

  protected transient ZoneController zoneController;

  public Character(Character_JSON json, final ZoneController zoneController) {
    super(json);

    this.type = "character";

    this.baseState = new BaseState(this);
    this.movingState = new MovingState(this);
    this.currentState = baseState;

    this.dir = json.getDir();
    sprite.changeSprite(dir.ordinal());

    this.frame = 0;
    this.left = false;

    this.zoneController = zoneController;
  }

  // Test constructor
  public Character(int X, int Y, Sprite sprite, Interaction interactBehavior, final ZoneController zoneController) {
    super(X, Y, sprite, interactBehavior, null);

    this.type = "character";

    this.baseState = new BaseState(this);
    this.movingState = new MovingState(this);
    this.currentState = baseState;

    this.dir = Direction.DOWN;
    this.left = false;

    this.zoneController = zoneController;
  }

  @Override
  public Image render() {
    update();
    return sprite.render();
  }

  public boolean canMove(Direction d) {
    return currentState.canMove(d);
  }
  public void move(Direction d) {
    currentState.move(d);
  }
  private void update() {
    currentState.update();
  }

  BaseState getBaseState() {
    return baseState;
  }
  MovingState getMovingState() {
    return movingState;
  }
  void changeState(CharacterState state) {
    this.currentState = state;
  }

  boolean canMoveDir(Direction d) {
    Point2D select = new Point2D(
        (d == Direction.LEFT) ? pos.getX() - 1 :
            (d == Direction.RIGHT) ? pos.getX() + 1 :
                pos.getX(),
        ((d == Direction.UP) ? pos.getY() - 1 :
            (d == Direction.DOWN) ? pos.getY() + 1 :
                pos.getY()));
    return zoneController.getEntityAt((int) select.getY(), (int) select.getX()) == null;
  }
  void moveCharacter(Direction d) {
    this.dir = d;

    switch (dir) {
      case UP:
        movePos(0, -1);
        break;
      case DOWN:
        movePos(0, 1);
        break;
      case LEFT:
        movePos(-1, 0);
        break;
      case RIGHT:
        movePos(1, 0);
        break;
    }
  }
  void updateSprite() {
    frame++;
    if (frame == 1) {
      sprite.changeSheet(1 + dir.ordinal());
      sprite.changeSprite((left) ? 0 : 1);
      left = !left;
    }
    else if (frame == 15) {
      sprite.changeSheet(0);
      sprite.changeSprite(dir.ordinal());
    }
    else if (frame == 30) {
      changeState(getBaseState());

      frame = 0;
    }
  }
}

