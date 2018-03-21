package bri64.PokemonEngine;

import bri64.PokemonEngine.ui.GameWindow;
import bri64.PokemonEngine.ui.Window;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    new App();
  }

  private App() {
    Platform.runLater(() -> start(new GameWindow()));
  }

  @Override
  public void start(Stage primaryStage) {
    if (primaryStage instanceof Window) {
      ((Window) primaryStage).init();
    }
    primaryStage.show();
  }
}
