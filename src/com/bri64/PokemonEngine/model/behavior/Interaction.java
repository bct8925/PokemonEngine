package com.bri64.PokemonEngine.model.behavior;

import com.bri64.PokemonEngine.model.Gerializable;
import com.bri64.PokemonEngine.model.entities.Entity;

/**
 * Interaction - [Strategy] adds behavior to an {@link Entity}
 */
public interface Interaction extends Gerializable {

  /**
   * Executes the behavior
   */
  void execute();
}
