package com.bri64.PokemonEngine.model;

import javafx.animation.AnimationTimer;

@SuppressWarnings("unused")
public class AnimatedSprite extends Sprite {

  private int speed;

  private AnimationTimer timer;
  private long lastTime;

  public AnimatedSprite(final String fileName, final int num, final int width, final int speed) {
    super(fileName, num, width);
    this.speed = speed;

    lastTime = 0;
    timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        if ((now - lastTime) / 1000000 >= speed) {
          setCurrent((currentIndex() <= getMax() - 2) ? currentIndex() + 1 : 0);
          lastTime = now;
        }

      }
    };
  }

  public void start() {
    timer.start();
  }
  public void stop() {
    timer.stop();
  }
}
