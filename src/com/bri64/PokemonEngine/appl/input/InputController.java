package com.bri64.PokemonEngine.appl.input;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputController {

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
    if (keyMap.containsKey(event.getCode())) {
      if (event.getEventType() == KeyEvent.KEY_PRESSED) {
        keyMap.get(event.getCode()).press();
      } else if (event.getEventType() == KeyEvent.KEY_RELEASED) {
        keyMap.get(event.getCode()).release();
      }
    }
  }
}
