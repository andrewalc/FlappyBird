package model;

import java.util.Random;
import java.awt.geom.Point2D;

import view.FlappyBirdView;

/**
 * Class representing a column in FlappyBird.
 */
public class Column {
  private int width = Player.RADIUS;
  private int posOrNeg = new Random().nextInt(2);

  private int gapHeight = new Random().nextInt(100) + 100;
  private int yOffset = new Random().nextInt(FlappyBirdView.WINDOW_HEIGHT / 3);
  private Point2D position;
  private boolean passed = false;

  Column(int x, int y) {
    this.position = new Point2D.Double(x, y);
  }

  boolean hasPassed() {
    return this.passed;
  }
}
