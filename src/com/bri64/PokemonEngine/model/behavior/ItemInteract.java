package com.bri64.PokemonEngine.model.behavior;

/**
 * ItemInteract - [{@link Interaction}] picks up an Item, then displays a {@linkplain MessageInteract Message}
 */
public class ItemInteract extends MessageInteract {

  //private Item item;

  /**
   *
   * @param message the message to display afterwards
   */
  public ItemInteract(/*Item item, */String message) {
    //this.item = item
    super(message);
  }

  /**
   * Gives the item, then displays a message
   */
  @Override
  public void execute() {
    // Get item
    //item.get();

    // Display message
    super.execute();
  }
}
