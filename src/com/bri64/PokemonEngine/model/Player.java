package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.input.KeyCode;

public class Player extends Character implements Observer {

  private transient InputController inputController;

  public Player(final InputController inputController, int X, int Y, Interaction interactBehavior,
      Interaction stepBehavior) {
    super(interactBehavior, stepBehavior);
    this.inputController = inputController;
    inputController.addObserver(this);

    this.sprite = new Sprite("/sprites/player.png", new SpriteData(16, 0, 16, 32));
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
