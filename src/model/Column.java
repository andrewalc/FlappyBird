package model;

import java.util.Random;
import java.awt.geom.Point2D;

import view.FlappyBirdView;

/**
 * Class representing a column in FlappyBird.
 */
public class Column {
  public static final double WIDTH = Player.RADIUS;
  public static final int BORDER_THRESHOLD = 40;

  private int gapHeight = new Random().nextInt(100) + 100;

  private int yOffset = new Random().nextInt(((FlappyBirdView.WINDOW_HEIGHT -
          gapHeight - BORDER_THRESHOLD) - BORDER_THRESHOLD) + 1) + BORDER_THRESHOLD;
  private Point2D position;
  private boolean passed = false;

  private boolean collided = false;


  Column(int x, int y) {
    this.position = new Point2D.Double(x, y);
  }

  public int getGapHeight() {
    return gapHeight;
  }

  public int getYOffset() {
    return yOffset;
  }

  public boolean isPassed() {
    return passed;
  }

  public void passed() {
    this.passed = true;
  }

  public void collision() {
    this.collided = true;
  }

  public boolean isCollided() {
    return collided;
  }

  public Point2D getPosition() {
    return position;
  }
}
