package com.bri64.Pokeman.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Animation {

  private long speed = 8L;    // Actual game speed

  private int frame;

  public int getFrame() {
    return frame;
  }

  private java.util.Timer timer = new Timer();

  public Animation() {
    this.frame = 1;
  }

  public void run(Runnable run, int endFrame) {
    Thread thread = new Thread(() -> {
      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
          run.run();
          if (frame >= endFrame) {
            timer.cancel();
          }
          frame++;
        }
      }, 0L, speed);
    });
    thread.start();
  }

  public void stopAnimation() throws IllegalStateException {
    timer.cancel();
  }
}
