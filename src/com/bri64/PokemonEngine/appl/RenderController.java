package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.entities.Player;
import com.bri64.PokemonEngine.model.zone.Zone;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderController {

  private static int PLAYER_OFFSET_X = 8; // player (width / 2)
  private static int PLAYER_OFFSET_Y = 6; // player (height / 2) + 4

  private GraphicsContext gc;
  private Point2D origin;

  public RenderController(final GraphicsContext graphicsContext) {
    this.gc = graphicsContext;
    this.origin = new Point2D((gc.getCanvas().getWidth() / 2) - PLAYER_OFFSET_X, (gc.getCanvas().getHeight() / 2) - PLAYER_OFFSET_Y);
  }

  public void clear() {
    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
  }

  public void moveOrigin(int x, int y) {
    this.origin = origin.add(x, y);
  }

  public void render(Zone z) {
    gc.drawImage(z.render(), z.getPos().getX() + origin.getX(), z.getPos().getY() + origin.getY());
  }
  public void renderPlayer(Player p) {
    Image image = p.render();
    gc.drawImage(image, (gc.getCanvas().getWidth() / 2) - (image.getWidth() / 2), (gc.getCanvas().getHeight() / 2) - (image.getHeight() / 2));
  }
}
