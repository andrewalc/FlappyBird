package model;

import java.util.ArrayList;

/**
 * Class representing the game state of flappy bird.
 */
public class GameState {

  private Player p1 = new Player();
  private ArrayList<Column> columns = new ArrayList<Column>();
  private int score = 0;
  private boolean inPlay = true;
}
