package com.bri64.PokemonEngine.appl.input;

public class KeyState {

  private boolean down;
  private boolean released;

  public boolean isDown() {
    return down;
  }

  public void press() {
    down = true;
  }
  public void release() {
    down = false;
    released = true;
  }

  public boolean isPressedOnce() {
    if (released && down) {
      released = false;
      return true;
    }
    return false;
  }

  public KeyState() {
    this.down = false;
    this.released = true;
  }
}
