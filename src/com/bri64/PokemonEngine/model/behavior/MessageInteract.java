package com.bri64.PokemonEngine.model.behavior;

/**
 * MessageInteract - [{@link Interaction}] displays a message
 */
public class MessageInteract extends Interaction {

  protected String message;

  /**
   * @param message the message itself
   */
  public MessageInteract(String message) {
    this.type = "message";

    this.message = message;
  }

  @Override
  public void init() {

  }

  /**
   * Display the message
   */
  @Override
  public void execute() {
    // Display message popup
    System.out.println(message);
  }


}
