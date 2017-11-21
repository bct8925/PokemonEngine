package com.bri64.PokemonEngine.appl;

import com.bri64.PokemonEngine.model.DrawableObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class RenderController {

  private GraphicsContext graphicsContext;
  private Point2D origin;

  public RenderController(final GraphicsContext graphicsContext) {
    this.graphicsContext = graphicsContext;
    this.origin = new Point2D(0, 0);
  }

  public void clear() {
    graphicsContext.clearRect(0, 0, graphicsContext.getCanvas().getWidth(), graphicsContext.getCanvas().getHeight());
  }
  public void draw(final DrawableObject obj) {
    graphicsContext.drawImage(obj.getSprite(), obj.getPos().getX() + origin.getX(), obj.getPos().getY() + origin.getY());
  }
}
