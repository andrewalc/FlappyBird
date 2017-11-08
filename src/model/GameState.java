package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import view.FlappyBirdField;
import view.FlappyBirdView;

/**
 * Class representing the game state of flappy bird.
 */
public class GameState {

  private Player p1 = new Player((int) FlappyBirdField.FIELD_DIM.getWidth() / 2, (int)
          FlappyBirdField.FIELD_DIM.getHeight() / 2);
  private ArrayList<Column> columns = new ArrayList<Column>();


  public int getScore() {
    return score;
  }

  private int score = 0;
  private boolean gameOver = false;

  public boolean isGameOver() {
    return this.gameOver;
  }


  /**
   * Method that handles KeyEvents for FlappyBird.
   *
   * @param keyEvent Int Keyevent to interpret.
   */
  public void keyMove(int keyEvent) {
    switch (keyEvent) {
      case KeyEvent.VK_SPACE:
        if (this.p1.isAlive()) {
          if (this.p1.getPosition().getY() > 0 && this.p1.getPosition().getY() < FlappyBirdView
                  .WINDOW_HEIGHT) {
            this.p1.getVelocity().setLocation(this.p1.getVelocity().getX(), -Player.ACCELERATION_JUMP);
            this.p1.getPosition().setLocation(this.p1.getPosition().getX(), this.p1.getPosition().getY() -
                    Player.ACCELERATION_JUMP);
            //this.playFlapSound();
          }
        }
        break;
      case KeyEvent.VK_R:
        this.restartGame();

      default:
        break;
    }
  }

  public void restartGame(){
    this.p1 = new Player((int) FlappyBirdField.FIELD_DIM.getWidth() / 2, (int)
            FlappyBirdField.FIELD_DIM.getHeight() / 2);
    this.columns = new ArrayList<Column>();
    this.score = 0;
  }

  public void onTick(int tickCount) {
    p1.move();
    if (p1.isAlive()) {
      checkCollisions();
      removeColumns();
      createColumn(tickCount);
      moveColumns();
      countScore();
    }
  }

  // creates a new column every 115th tick
  public void createColumn(int tickCount) {
    if (tickCount % 55 == 0) {
      this.columns.add(new Column((int) FlappyBirdField.FIELD_DIM.getWidth() + Player.RADIUS,
              0));
    }
  }

  // transitions all columns to the left
  public void moveColumns() {
    for (Column c : this.columns) {
      c.getPosition().setLocation(c.getPosition().getX() - 4.5, c.getPosition().getY());
    }
  }

  // removes columns that have gone offscreen on the left
  public void removeColumns() {
    if (this.columns.size() > 1) {
      if (this.columns.get(0).getPosition().getX() < 0) {
        this.columns.remove(0);
      }
    }
  }

  // updates the current score
  public void countScore() {
    for (Column c : this.columns) {
      if (!c.isPassed() && p1.hasPassedColumn(c)) {
        this.score++;
        //this.playScoreSound();
      }
    }
  }

  public void checkCollisions() {
    for (Column c : this.columns) {
      if (!c.isPassed() && p1.isAlive() && this.collisionCheck(this.p1, c)) {
        System.out.println("COLLISION");
        c.collision();
        p1.kill();
        //this.playDeathSounds();
      }
    }
  }

  public boolean collisionCheck(Player p, Column c) {
    // collisions for columns with upward offset

    if (!c.isPassed() && !c.isCollided() && (Math.abs(p.getPosition().getX() - c.getPosition()
            .getX()) < Player.RADIUS * 2)) {
      System.out.println("contemplating");
      return p.getPosition().getY() > c.getYOffset() + c.getGapHeight() || p.getPosition().getY() <
              c.getYOffset();
    }
    // collision for when the player hits the floor
    return p1.getPosition().getY() >= FlappyBirdView.WINDOW_HEIGHT - Player.RADIUS * 3;
  }


  public Player getPlayer() {
    return this.p1;
  }

  public ArrayList<Column> getColumns() {
    return this.columns;
  }
}
