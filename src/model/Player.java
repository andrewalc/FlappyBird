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
  private boolean isAlive = true;
  public static final int RADIUS = 16;
  public static final double ACCELERATION = 1;
  public static final double ACCELERATION_JUMP = 6;
  public static final double DECCELERATION_FACTOR = (double) .31;

  Player(int x, int y) {
    this.position = new Point2D.Double(x, y);
    this.velocity = new Point2D.Double(0, 0);
  }

  void gravity() {
    if (this.position.getY() < FlappyBirdView.WINDOW_HEIGHT - RADIUS) {
      this.position.setLocation(this.position.getX(), this.position.getY() + velocity.getY());
      this.velocity.setLocation(this.velocity.getX(), this.velocity.getY() + DECCELERATION_FACTOR);
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
        if (isAlive) {
          if (this.position.getY() > 0 && this.position.getY() < FlappyBirdView.WINDOW_HEIGHT) {
            this.velocity.setLocation(this.velocity.getX(), this.velocity.getY() -
                    ACCELERATION_JUMP);
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
