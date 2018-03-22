package com.bri64.PokemonEngine.model.entities;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.appl.input.InputController;
import com.bri64.PokemonEngine.model.behavior.Interaction;
import com.bri64.PokemonEngine.model.sprite.Sprite;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class Player extends Character implements Observer {

  public static String SPRITE_SRC = "/sprites/player.png";

  private transient InputController inputController;
  private transient RenderController renderController;
  private transient ZoneController zoneController;

  public Player(int X, int Y, Sprite sprite, final InputController inputController, final RenderController renderController, final ZoneController zoneController, Interaction interactBehavior,
      Interaction stepBehavior) {
    super(X, Y, sprite, zoneController, interactBehavior, stepBehavior);

    this.zoneController = zoneController;
    this.renderController = renderController;
    this.inputController = inputController;
    inputController.addObserver(this);
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
  public void update(Observable o, Object arg) {
    Map<KeyCode, Runnable> exec = new HashMap<>();
    exec.put(KeyCode.UP, () -> {
      if (inputController.getKeyState(KeyCode.UP).isPressedOnce()) {
        if (move(Direction.UP)) {
          renderController.moveOrigin(0, 16);
        }
      }
    });
    exec.put(KeyCode.DOWN, () -> {
      if (inputController.getKeyState(KeyCode.DOWN).isPressedOnce()) {
        if (move(Direction.DOWN)) {
          renderController.moveOrigin(0, -16);
        }
      }
    });
    exec.put(KeyCode.LEFT, () -> {
      if (inputController.getKeyState(KeyCode.LEFT).isPressedOnce()) {
        if (move(Direction.LEFT)) {
          renderController.moveOrigin(16, 0);
        }
      }
    });
    exec.put(KeyCode.RIGHT, () -> {
      if (inputController.getKeyState(KeyCode.RIGHT).isPressedOnce()) {
        if (move(Direction.RIGHT)) {
          renderController.moveOrigin(-16, 0);
        }
      }
    });

    exec.put(KeyCode.E, () -> {
      if (inputController.getKeyState(KeyCode.E).isPressedOnce()) {
        interactWith();
      }
    });
    Runnable run = exec.get(arg);
    if (run != null) run.run();
  }
}
