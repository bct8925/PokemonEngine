package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import com.bri64.PokemonEngine.model.sprite.SpriteData;
import com.bri64.PokemonEngine.model.sprite.SpriteSheet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;

public class Player extends Character implements Observer {

  private static String SPRITE_SRC = "/sprites/player.png";

  private transient InputController inputController;

  public Player(final InputController inputController, int X, int Y, Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);

    this.inputController = inputController;
    inputController.addObserver(this);

    List<SpriteData> playerSprites = new ArrayList<>();
    playerSprites.add(new SpriteData(0, 0, 16, 32));
    playerSprites.add(new SpriteData(16, 0, 16, 32));
    playerSprites.add(new SpriteData(32, 0, 16, 32));
    playerSprites.add(new SpriteData(48, 0, 16, 32));
    this.sprite = new Sprite(0, 0, SPRITE_SRC, new SpriteSheet(SPRITE_SRC, playerSprites));
    sprite.changeSprite(dir.ordinal());
    this.setPos(X, Y);
  }

  @Override
  public void update(Observable o, Object arg) {
    Map<KeyCode, Runnable> exec = new HashMap<>();
    exec.put(KeyCode.UP, () -> {
      if (inputController.getKeyState(KeyCode.UP).isPressedOnce()) {
        move(Direction.UP);
      }
    });
    exec.put(KeyCode.DOWN, () -> {
      if (inputController.getKeyState(KeyCode.DOWN).isPressedOnce()) {
        move(Direction.DOWN);
      }
    });
    exec.put(KeyCode.LEFT, () -> {
      if (inputController.getKeyState(KeyCode.LEFT).isPressedOnce()) {
        move(Direction.LEFT);
      }
    });
    exec.put(KeyCode.RIGHT, () -> {
      if (inputController.getKeyState(KeyCode.RIGHT).isPressedOnce()) {
        move(Direction.RIGHT);
      }
    });
    Runnable run = exec.get(arg);
    if (run != null) run.run();
  }
}
