package com.bri64.PokemonEngine.model.behavior;

import com.bri64.PokemonEngine.model.entities.Player;
import com.bri64.PokemonEngine.model.zone.Zone;

/**
 * PortalInteract - [{@link Interaction}] Teleports {@link Player} between {@link Zone}s
 */
public class PortalInteract extends Interaction {

  public PortalInteract() {
    this.type = "portal";
  }

  @Override
  public void init() {

  }

  /**
   *
   */
  @Override
  public void execute() {
    // Change zone
    System.out.println("change zone");
  }
}
