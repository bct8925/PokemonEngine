package com.bri64.PokemonEngine.model.entities.character.player;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.entities.Entity;
import com.bri64.PokemonEngine.model.entities.character.Character;
import com.bri64.PokemonEngine.model.entities.character.Direction;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Player extends Character {

  public static String SPRITE_SRC = "/sprites/player.png";

  private transient InputController inputController;
  private transient RenderController renderController;

  public Player(int X, int Y, final RenderController renderController, final InputController inputController, final ZoneController zoneController) {
    super(X, Y, null, null, zoneController);

    this.renderController = renderController;
    this.inputController = inputController;

    List<SpriteSheet> sheets = new ArrayList<>();
    List<SpriteData> data = new ArrayList<>();
    data.add(new SpriteData(0, 0, 16, 20));
    data.add(new SpriteData(16, 0, 16, 20));
    data.add(new SpriteData(32, 0, 16, 20));
    data.add(new SpriteData(48, 0, 16, 20));
    sheets.add(new SpriteSheet(SPRITE_SRC, data));
    for (int i = 0; i < 4; i++) {
      data = new ArrayList<>();
      data.add(new SpriteData(64 + (i * 32), 0, 16, 20));
      data.add(new SpriteData(64 + (i * 32) + 16, 0, 16, 20));
      sheets.add(new SpriteSheet(SPRITE_SRC, data));
    }
    this.sprite = new Sprite(sheets);
    sprite.changeSprite(dir.ordinal());
  }

  private void interactWith() {
    Point2D select = new Point2D(
        (dir == Direction.LEFT) ? pos.getX() - 1 :
            (dir == Direction.RIGHT) ? pos.getX() + 1 :
                pos.getX(),
        ((dir == Direction.UP) ? pos.getY() - 1 :
            (dir == Direction.DOWN) ? pos.getY() + 1 :
                pos.getY()));
    //System.out.println("[" + pos.getX() + "," + pos.getY() + "] -> [" + select.getX() + "," + select.getY() + "]");

    Entity entity = zoneController.getEntityAt((int) select.getY(), (int) select.getX());
    if (entity != null) {
      entity.interact();
    }
  }

  @Override
  public Image render() {
    updateInput();
    return super.render();
  }
  
  private void updateInput() {
    if (inputController.getKeyState(KeyCode.UP).isDown()) {
      if (canMove(Direction.UP)) {
        move(Direction.UP);
        renderController.moveOrigin(0, 16);
      }
    }
    else if (inputController.getKeyState(KeyCode.DOWN).isDown()) {
      if (canMove(Direction.DOWN)) {
        move(Direction.DOWN);
        renderController.moveOrigin(0, -16);
      }
    }
    else if (inputController.getKeyState(KeyCode.LEFT).isDown()) {
      if (canMove(Direction.LEFT)) {
        move(Direction.LEFT);
        renderController.moveOrigin(16, 0);
      }
    }
    else if (inputController.getKeyState(KeyCode.RIGHT).isDown()) {
      if (canMove(Direction.RIGHT)) {
        move(Direction.RIGHT);
        renderController.moveOrigin(-16, 0);
      }
    }

    if (inputController.getKeyState(KeyCode.E).isPressedOnce()) {
      interactWith();
    }
  }
}
