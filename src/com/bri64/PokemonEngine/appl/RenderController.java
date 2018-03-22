package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.entities.Player;
import com.bri64.PokemonEngine.model.zone.Zone;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RenderController {

  private GraphicsContext gc;
  private Point2D origin;

  public RenderController(final GraphicsContext graphicsContext) {
    this.gc = graphicsContext;
    this.origin = new Point2D((gc.getCanvas().getWidth() / 2) - 8, (gc.getCanvas().getHeight() / 2) - 6);
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
