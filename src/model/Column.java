package model;

import java.util.Random;
import java.awt.geom.Point2D;

import view.FlappyBirdView;

/**
 * Class representing a column in FlappyBird.
 */
public class Column {

  // Represents the wall's position.
  private Point2D position;

  // Has this column been passed by the player?
  private boolean passed = false;

  // Has this column been collided into?
  private boolean collided = false;

  // Randomly generated height of this columns gap space.
  private int gapHeight = new Random().nextInt(100) + 100;

  // Represents the y value where the gap in the column begins.
  private int yOffset = new Random().nextInt(((FlappyBirdView.WINDOW_HEIGHT -
          gapHeight - BORDER_THRESHOLD) - BORDER_THRESHOLD) + 1) + BORDER_THRESHOLD;

  // Constants
  public static final double WIDTH = Player.RADIUS;
  public static final int BORDER_THRESHOLD = 40;

  /**
   * Construtor for a Column. Requires an int x and y for the Columns starting position.
   *
   * @param x X val.
   * @param y Y val.
   */
  Column(int x, int y) {
    this.position = new Point2D.Double(x, y);
  }

  /**
   * Getter for this column's current position.
   *
   * @return The column's current position.
   */
  public Point2D getPosition() {
    return position;
  }

  /**
   * Getter to see if this column has been passed.
   *
   * @return If this column has been passed.
   */
  public boolean isPassed() {
    return passed;
  }


  /**
   * Getter to see if this column has been collided into.
   *
   * @return If this column has been collided into.
   */
  public boolean isCollided() {
    return collided;
  }

  /**
   * Getter for this column's gap height.
   *
   * @return The column's gap height.
   */
  public int getGapHeight() {
    return gapHeight;
  }

  /**
   * Getter for this column's gap's y offset.
   *
   * @return This column's gap's y offset.
   */
  public int getYOffset() {
    return yOffset;
  }

  /**
   * Called when this column has been passed, updates passed boolean to true.
   */
  public void passed() {
    this.passed = true;
  }

  /**
   * Called when this column has been collided into, updates collided boolean to true.
   */
  public void collision() {
    this.collided = true;
  }


}
