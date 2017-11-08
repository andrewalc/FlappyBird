package model;

import java.awt.geom.Point2D;

import view.FlappyBirdView;

/**
 * Class representing the player in FlappyBird.
 */
public class Player {
  // Represents the player's position
  private Point2D position;

  // Represents the player's velocity
  private Point2D velocity;

  // Is the player alive?
  private boolean alive = true;

  // Constants
  public static final int RADIUS = 12;
  public static final double ACCELERATION = 1;
  public static final double ACCELERATION_JUMP = 15;
  public static final double DECCELERATION_FACTOR = (double) 2;

  /**
   * Player Constructor. Requires ints x and y, for the player's starting position.
   *
   * @param x X val.
   * @param y Y val.
   */
  Player(int x, int y) {
    this.position = new Point2D.Double(x, y);
    this.velocity = new Point2D.Double(0, 0);
  }

  /**
   * Getting for the player's Point2D position.
   *
   * @return The player's position
   */
  public Point2D getPosition() {
    return position;
  }

  /**
   * Getting for the player's Point2D velocity.
   *
   * @return The player's velocity.
   */
  public Point2D getVelocity() {
    return velocity;
  }

  /**
   * Getter to check if the player is alive.
   *
   * @return Whether or not the player is alive.
   */
  public boolean isAlive() {
    return alive;
  }

  /**
   * Kills the player, setting alive boolean to false.
   */
  public void kill() {
    this.alive = false;
  }

  /**
   * Returns whether this player has passed the given Column. If so, returns true and updates the
   * column's passed boolean to true.
   *
   * @param c The column to test.
   * @return Whether this player has passed column c.
   */
  protected boolean hasPassedColumn(Column c) {
    if (this.position.getX() - (c.getPosition().getX() + Column.WIDTH) > 0) {
      c.passed();
      return true;
    }
    return false;
  }

  /**
   * On tick, will update the player's position using its current velocity. Takes player input and
   * gravity into account.
   */
  void move() {
    // limit max upward velocity
    if (this.velocity.getY() < -ACCELERATION_JUMP) {
      this.velocity.setLocation(this.velocity.getX(), -ACCELERATION_JUMP);
    }

    // Gravity aspect
    if (this.position.getY() <= FlappyBirdView.WINDOW_HEIGHT - RADIUS * 3) {
      // TODO: Why do i need to do multiply radius times 3???
      this.position.setLocation(this.position.getX(), this.position.getY() + velocity.getY());
      this.velocity.setLocation(this.velocity.getX(), this.velocity.getY() + DECCELERATION_FACTOR);
    }

    // Ground prevention
    if (this.position.getY() >= FlappyBirdView.WINDOW_HEIGHT) {
      this.position.setLocation(this.position.getX(), FlappyBirdView.WINDOW_HEIGHT - RADIUS * 3);
      this.velocity.setLocation(0, 0);
    }
  }

}
