package com.bri64.PokemonEngine.model;

import com.bri64.PokemonEngine.appl.RenderController;
import javafx.geometry.Point3D;
import javafx.scene.image.WritableImage;

public class Player extends DrawableObject {

  private RenderController renderController;
  private AnimatedSprite sprite;

  public Player(final RenderController renderController) {
    this.renderController = renderController;
    this.sprite = new AnimatedSprite("/sprites/player.png", 24, 16, 500);
    this.pos = new Point3D(400, 300, 0);
    sprite.start();
  }

  @Override
  public WritableImage getSprite() {
    return sprite.currentSprite();
  }

  @Override
  public void render() {
    renderController.draw(this);
  }
}
