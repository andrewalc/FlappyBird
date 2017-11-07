package model;

import java.util.ArrayList;

import view.FlappyBirdView;

/**
 * Class representing the game state of flappy bird.
 */
public class GameState {

  private Player p1 = new Player(FlappyBirdView.WINDOW_HEIGHT / 2, FlappyBirdView.WINDOW_WIDTH / 2);
  private ArrayList<Column> columns = new ArrayList<Column>();
  private int score = 0;
  private boolean inPlay = true;
}
