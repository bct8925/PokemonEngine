package com.bri64.PokemonEngine.ui;

import com.bri64.PokemonEngine.appl.RenderController;
import com.bri64.PokemonEngine.model.Game;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

public class GameWindow extends Window {
  private String WINDOW_TITLE = "Pokeman";
  private int WINDOW_WIDTH = 800;
  private int WINDOW_HEIGHT = 600;

  private Canvas canvas;
  private RenderController renderController;
  private Game game;

  public GameWindow() {
    // Initialize stage
    super();

    // Set window title
    setTitle(WINDOW_TITLE);

    // Set content
    GridPane root = new GridPane();
    canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);

    root.getChildren().add(canvas);

    // Set scene
    setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
  }

  @Override
  public void init() {
    this.renderController = new RenderController(canvas.getGraphicsContext2D());
    this.game = new Game(renderController);
  }
}
