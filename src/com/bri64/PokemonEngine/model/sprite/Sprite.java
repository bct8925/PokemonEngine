package com.bri64.PokemonEngine.model.sprite;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.Renderable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

public class Sprite extends Renderable implements Gerializable {

  private String path;
  private List<SpriteSheet> spriteSheets;

  private transient SpriteSheet current;

  public Sprite(int x, int y, String PATH, SpriteSheet SHEET) {
    this.path = PATH;
    this.spriteSheets = new ArrayList<>();
    spriteSheets.add(SHEET);

    this.current = spriteSheets.get(0);
    this.setPos(x, y);

    init();
  }

  public Sprite(String PATH, List<SpriteSheet> SHEETS) {
    this.path = PATH;
    this.spriteSheets = SHEETS;

    this.current = spriteSheets.get(0);
    this.setPos(0, 0);

    init();
  }

  @Override
  public void init() {
    for (SpriteSheet s : spriteSheets) {
      s.init();
    }
    this.current = spriteSheets.get(0);
  }

  public void changeSprite(int index) {
    current.setCurrent(index);
  }

  public void changeSheet(int index) {
    current = spriteSheets.get(index);
  }

  @Override
  public Image render() {
    return current.getCurrent().render();
  }
}
