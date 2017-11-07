package model;

import java.awt.Point;

/**
 * Class representing the player in FlappyBird.
 */
public class Player {
  private Point position;
  private Point velocity;
  private boolean isAlive = true;
  public static final int RADIUS = 16;
  public static final double ACCELERATION = 1;
  public static final double ACCELERATION_JUMP = 6;
  public static final double DECCELERATION_FACTOR = (double) .31;

  Player(int x, int y) {
    this.position = new Point(x, y);
    this.velocity = new Point(0, 0);
  }

  


}
