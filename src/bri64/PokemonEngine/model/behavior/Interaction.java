package bri64.PokemonEngine.model.behavior;

import bri64.PokemonEngine.model.Entity;

/**
 * Interaction - [Strategy] adds behavior to an {@link Entity}
 */
public interface Interaction {

  /**
   * Executes the behavior
   */
  void execute();
}
