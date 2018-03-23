package com.bri64.PokemonEngine.model.behavior;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.entities.Entity;

/**
 * Interaction - [Strategy] adds behavior to an {@link Entity}
 */
public abstract class Interaction implements Gerializable {

  protected String type;

  /**
   * Executes the behavior
   */
  public abstract void execute();
}
