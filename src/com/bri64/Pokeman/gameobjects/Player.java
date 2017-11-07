package com.bri64.Pokeman.gameobjects;

import com.bri64.Pokeman.Pokeman;
import com.bri64.Pokeman.utils.Animation;
import java.awt.image.BufferedImage;

@SuppressWarnings("WeakerAccess")
public class Player extends Box {

  private Pokeman main;
  private Sprite sprite;
  private Animation ani;

  public Sprite getSprite() {
    return sprite;
  }

  private int direction;
  private boolean moving = false;
  private boolean stopMoving = false;
  private boolean animating = false;
  private boolean animatingWall = false;
  private boolean leftFacing = true;

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public boolean isStopMoving() {
    return stopMoving;
  }

  public void setStopMoving(boolean stopMoving) {
    this.stopMoving = stopMoving;
  }

  public boolean isAnimating() {
    return animating;
  }

  public Tile isFacing() {
    if (direction == 0) {
      return main.getCurZone().getTile(location[0], location[1] - 1);
    } else if (direction == 1) {
      return main.getCurZone().getTile(location[0], location[1] + 1);
    } else if (direction == 2) {
      return main.getCurZone().getTile(location[0] - 1, location[1]);
    } else if (direction == 3) {
      return main.getCurZone().getTile(location[0] + 1, location[1]);
    }
    return null;
  }

  private int zoom = 2;
  private int[] location;

  public BufferedImage getImage() {
    return sprite.getCurImage();
  }

  public int getZoomWidth() {
    return zoom * sprite.getWidth();
  }

  public int getZoomHeight() {
    return zoom * sprite.getHeight();
  }

  public void setLocation(int x, int y) {
    location[0] = x;
    location[1] = y;
    main.setBgX((main.getWindowWidth() / 2) - 10 - (x * main.getCurZone().getTileZoom()));
    main.setBgY((main.getWindowHeight() / 2) - 5 - (y * main.getCurZone().getTileZoom()));
  }

  public Player(Pokeman main, Sprite sprite) {
    super(sprite.getCurImage().getWidth(), sprite.getCurImage().getHeight());
    this.main = main;
    this.sprite = sprite;
    this.direction = sprite.getCurIndex();
    ani = new Animation();
    location = new int[2];
    location[0] = 0;
    location[1] = 0;
  }

  public void move(int dir, boolean sprint) {     // Player movement
    if (!moving && direction
        != dir) {  // If not moving and current direction is not the new direction, turn
      if (animatingWall) {        // If wall animation still going, stop it
        ani.stopAnimation();
        animatingWall = false;
      }
      ani = new Animation();  // New animation
      animating = true;
      ani.run(() -> aniTurn(dir, ani.getFrame(), main.getCurZone().getTileZoom() / 2),
          main.getCurZone().getTileZoom() / 2);  // Run turning animation
      direction = dir;    // Set new direction
    } else {  // Otherwise, walk
      direction = dir;    // Set new direction
      if (dir == 0 && !main.getCurZone().getTile(location[0], location[1] - 1)
          .isSolid()) {         // If moving up and new tile is not solid
        moving = true;  // Set moving
        if (animatingWall) {    // If wall animation still going, stop it
          ani.stopAnimation();
          animatingWall = false;
        }
        ani = new Animation();  // New animation
        animating = true;
        location[1] -= 1;   // Move position in grid up
        if (!sprint) {  // If not sprinting
          ani.run(() -> aniMove(dir, ani.getFrame(), main.getCurZone().getTileZoom()),
              main.getCurZone().getTileZoom());          // Start walking animation
        } else {        // If sprinting
          ani.run(() -> aniRun(dir, ani.getFrame(), main.getCurZone().getTileZoom() / 2),
              main.getCurZone().getTileZoom() / 2);   // Start running animation
        }
      } else if (dir == 1 && !main.getCurZone().getTile(location[0], location[1] + 1)
          .isSolid()) {   // If moving down and new tile is not solid
        moving = true;
        if (animatingWall) {
          ani.stopAnimation();
          animatingWall = false;
        }
        ani = new Animation();
        animating = true;
        location[1] += 1;   // Move position in grid down
        if (!sprint) {
          ani.run(() -> aniMove(dir, ani.getFrame(), main.getCurZone().getTileZoom()),
              main.getCurZone().getTileZoom());
        } else {
          ani.run(() -> aniRun(dir, ani.getFrame(), main.getCurZone().getTileZoom() / 2),
              main.getCurZone().getTileZoom() / 2);
        }
      } else if (dir == 2 && !main.getCurZone().getTile(location[0] - 1, location[1])
          .isSolid()) {    // If moving left and new tile is not solid
        moving = true;
        if (animatingWall) {
          ani.stopAnimation();
          animatingWall = false;
        }
        ani = new Animation();
        animating = true;
        location[0] -= 1;   // Move position in grid left
        if (!sprint) {
          ani.run(() -> aniMove(dir, ani.getFrame(), main.getCurZone().getTileZoom()),
              main.getCurZone().getTileZoom());
        } else {
          ani.run(() -> aniRun(dir, ani.getFrame(), main.getCurZone().getTileZoom() / 2),
              main.getCurZone().getTileZoom() / 2);
        }
      } else if (dir == 3 && !main.getCurZone().getTile(location[0] + 1, location[1])
          .isSolid()) {    // If moving right and new tile is not solid
        moving = true;
        if (animatingWall) {
          ani.stopAnimation();
          animatingWall = false;
        }
        ani = new Animation();
        animating = true;
        location[0] += 1;   // Move position in grid right
        if (!sprint) {
          ani.run(() -> aniMove(dir, ani.getFrame(), main.getCurZone().getTileZoom()),
              main.getCurZone().getTileZoom());
        } else {
          ani.run(() -> aniRun(dir, ani.getFrame(), main.getCurZone().getTileZoom() / 2),
              main.getCurZone().getTileZoom() / 2);
        }
      } else {  // If solid tile
        if (!animatingWall) {   // If not already walking into a wall, do it
          ani = new Animation();
          animatingWall = true;
          ani.run(() -> aniWall(dir, ani.getFrame(), main.getCurZone().getTileZoom() * 2),
              main.getCurZone().getTileZoom() * 2);  // Start wall animation
        }
      }
    }
  }

  public void setup(Zone curZone) {
    setLocation(curZone.getSpawnX(), curZone.getSpawnY());
    setDirection(curZone.getSpawnDir());
    getSprite().changeSprite(curZone.getSpawnDir());
  }

  // Animations
  private void aniTurn(int dir, int frame, int endFrame) {
    if (frame == 1) {
      switch (dir) {
        case 0:
          sprite.changeSprite(4 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 1:
          sprite.changeSprite(6 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 2:
          sprite.changeSprite(8 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 3:
          sprite.changeSprite(10 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        default:
          break;
      }
    } else if (frame == (endFrame / 2)) {
      sprite.changeSprite(dir);
    }
    main.repaint();
    if (frame == endFrame) {
      animating = false;
      main.repaint();
    }
  }

  private void aniMove(int dir, int frame, int endFrame) {
    if (frame == 1) {
      sprite.changeSprite((4 + (2 * dir)) + ((leftFacing) ? 1 : 0));
      leftFacing = !leftFacing;
    } else if (frame == (endFrame / 2)) {
      sprite.changeSprite(dir);
    }
    switch (dir) {
      case 0:
        main.setBgY(main.getBgY() + 1);
        break;
      case 1:
        main.setBgY(main.getBgY() - 1);
        break;
      case 2:
        main.setBgX(main.getBgX() + 1);
        break;
      case 3:
        main.setBgX(main.getBgX() - 1);
        break;
      default:
        break;
    }
    main.repaint();
    if (frame == endFrame) {

      if (main.getCurZone().getTile(location[0], location[1]).isDoor()) {
        main.doorZone(main.getCurZone().getTile(location[0], location[1]));
      } else {
        animating = false;
        if (stopMoving) {
          sprite.changeSprite(dir);
          moving = false;
          stopMoving = false;
        }
      }
      main.repaint();
    }
  }

  private void aniRun(int dir, int frame, int endFrame) {
    if (frame == 1) {
      sprite.changeSprite((13 + (3 * dir)) + ((leftFacing) ? 1 : 0));
      leftFacing = !leftFacing;
    } else if (frame == (endFrame / 2)) {
      sprite.changeSprite(12 + (3 * dir));
    }
    switch (dir) {
      case 0:
        main.setBgY(main.getBgY() + 2);
        break;
      case 1:
        main.setBgY(main.getBgY() - 2);
        break;
      case 2:
        main.setBgX(main.getBgX() + 2);
        break;
      case 3:
        main.setBgX(main.getBgX() - 2);
        break;
      default:
        break;
    }
    main.repaint();
    if (frame == endFrame) {
      //main.getPlayer().getSprite().changeSprite(dir);
      if (main.getCurZone().getTile(location[0], location[1]).isDoor()) {
        main.doorZone(main.getCurZone().getTile(location[0], location[1]));
      } else {
        animating = false;
        if (stopMoving) {
          sprite.changeSprite(dir);
          moving = false;
          stopMoving = false;
        }
      }
      main.repaint();
    }
  }

  private void aniWall(int dir, int frame, int endFrame) {
    if (frame == 1) {
      switch (dir) {
        case 0:
          sprite.changeSprite(4 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 1:
          sprite.changeSprite(6 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 2:
          sprite.changeSprite(8 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        case 3:
          sprite.changeSprite(10 + ((leftFacing) ? 1 : 0));
          leftFacing = !leftFacing;
          break;
        default:
          break;
      }
    } else if (frame == (endFrame / 2)) {
      sprite.changeSprite(dir);
    }
    main.repaint();
    if (frame == endFrame) {
      animatingWall = false;
      main.repaint();
    }
  }
}
