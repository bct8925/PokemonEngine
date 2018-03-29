package com.bri64.PokemonEngine.model.behavior;

import com.bri64.PokemonEngine.appl.ZoneController;
import com.bri64.PokemonEngine.model.entities.character.player.Player;
import com.bri64.PokemonEngine.model.zone.Zone;
import javafx.geometry.Point2D;

/**
 * PortalInteract - [{@link Interaction}] Teleports {@link Player} between {@link Zone}s
 */
public class PortalInteract extends Interaction {

  private String destZone;
  private Point2D destPos;

  public String getZone() {
    return destZone;
  }
  public Point2D getDestPos() {
    return destPos;
  }

  private transient ZoneController zoneController;

  public PortalInteract(String destZone, int col, int row, final ZoneController zoneController) {
    this.type = "portal";

    this.destZone = destZone;
    this.destPos = new Point2D(col, row);

    this.zoneController = zoneController;
    init();
  }

  /**
   *
   */
  @Override
  public void execute() {
    // Change zone
    System.out.println("change zone");
  }

  @Override
  public void init() {

  }
}
