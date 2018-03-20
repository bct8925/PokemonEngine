package com.bri64.PokemonEngine.model;

import java.util.List;

public class Zone extends Renderable {

  private Background background;
  private List<Renderable> drawables;

  public Zone() {
    this.background = new Background();
    this.layer = RenderLayer.BG;
  }

  @Override
  public void render() {
    background.render();
    for (Renderable r : drawables) {
      r.render();
    }
  }
}
