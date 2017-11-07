package model;

import java.awt.Point;
import java.util.Random;

import view.FlappyBirdView;

/**
 * Class representing a column in FlappyBird.
 */
public class Column {
  private int width = Player.RADIUS;
  private int posOrNeg = new Random().nextInt(2);

  private int gapHeight = new Random().nextInt(100) + 100;
  private int yOffset = new Random().nextInt(FlappyBirdView.WINDOW_HEIGHT / 3);
  private Point position;
  private boolean passed = false;
}
