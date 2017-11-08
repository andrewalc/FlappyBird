package model;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;

import view.FlappyBirdView;

/**
 * Class representing the player in FlappyBird.
 */
public class Player {
  private Point2D position;
  private Point2D velocity;


  private boolean alive = true;
  public static final int RADIUS = 12;
  public static final double ACCELERATION = 1;
  public static final double ACCELERATION_JUMP = 15;
  public static final double DECCELERATION_FACTOR = (double) 2;

  Player(int x, int y) {
    this.position = new Point2D.Double(x, y);
    this.velocity = new Point2D.Double(0, 0);
  }

  public Point2D getPosition() {
    return position;
  }

  public Point2D getVelocity() {
    return velocity;
  }


  public boolean isAlive() {
    return alive;
  }

  public void kill() {
    this.alive = false;
  }

  boolean hasPassedColumn(Column c) {
    if ((this.position.getX() - Player.RADIUS) - (c.getPosition().getX() + Column.WIDTH) > 0) {
      c.passed();
      return true;
    }
    return false;
  }

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
    if (this.position.getY() >= FlappyBirdView.WINDOW_HEIGHT - RADIUS * 3) {
      this.position.setLocation(this.position.getX(), FlappyBirdView.WINDOW_HEIGHT - RADIUS * 3);
      this.velocity.setLocation(0, 0);
    }
  }

  /**
   * Method that handles KeyEvents for the Craft. Includes directional movement, reloading, and
   * shooting bullets.
   *
   * @param keyEvent Int Keyevent to interpret.
   */
  public void keyMove(int keyEvent) {
    switch (keyEvent) {
      case KeyEvent.VK_SPACE:
        if (alive) {
          if (this.position.getY() > 0 && this.position.getY() < FlappyBirdView.WINDOW_HEIGHT) {
            this.velocity.setLocation(this.velocity.getX(), -ACCELERATION_JUMP);
            this.position.setLocation(this.position.getX(), this.position.getY() -
                    ACCELERATION_JUMP);
            //this.playFlapSound();
          }
        }
        break;

      default:
        break;
    }
  }


}
