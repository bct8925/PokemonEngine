package com.bri64.PokemonEngine.appl.input;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.event.EventType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputController extends Observable {

  private Map<KeyCode, KeyState> keyMap;

  public InputController() {
    this.keyMap = new HashMap<>();
    keyMap.put(KeyCode.UP, new KeyState());
    keyMap.put(KeyCode.DOWN, new KeyState());
    keyMap.put(KeyCode.LEFT, new KeyState());
    keyMap.put(KeyCode.RIGHT, new KeyState());

    keyMap.put(KeyCode.E, new KeyState());
  }

  public KeyState getKeyState(KeyCode code) {
    return keyMap.get(code);
  }

  public void doEvent(KeyEvent event) {
    Map<EventType<KeyEvent>, Runnable> exec = new HashMap<>();
    exec.put(KeyEvent.KEY_PRESSED, () -> {
      if (keyMap.containsKey(event.getCode())) {
        keyMap.get(event.getCode()).press();
        setChanged();
        notifyObservers(event.getCode());
      }
    });
    exec.put(KeyEvent.KEY_RELEASED, () -> {
      if (keyMap.containsKey(event.getCode())) {
        keyMap.get(event.getCode()).release();
        setChanged();
        notifyObservers(event.getCode());
      }
    });

    Runnable run = exec.get(event.getEventType());
    if (run != null) run.run();
  }
}
