package bri64.PokemonEngine.model.behavior;

/**
 * MessageInteract - [{@link Interaction}] displays a message
 */
public class MessageInteract implements Interaction {

  protected String message;

  /**
   * @param message the message itself
   */
  public MessageInteract(String message) {
    this.message = message;
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
