package com.bri64.PokemonEngine.model.behavior;

public class MessageInteract implements Behavior {

  private String message;

  public MessageInteract(String message) {
    this.message = message;
  }

  @Override
  public void execute() {
    // Display message popup
    System.out.println(message);
  }
}
