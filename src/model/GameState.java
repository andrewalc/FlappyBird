package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;

import view.FlappyBirdField;
import view.FlappyBirdView;

/**
 * Class representing the game state of flappy bird.
 */
public class GameState {

  // The player.
  private Player p1 = new Player((int) FlappyBirdField.FIELD_DIM.getWidth() / 2, (int)
          FlappyBirdField.FIELD_DIM.getHeight() / 2);

  // List of all columns.
  private ArrayList<Column> columns = new ArrayList<Column>();

  // Stack for FlappySounds.
  private Stack<FlappySounds> soundsStack = new Stack<>();

  // Current Score
  private int score = 0;

  // Game high score.
  private int highScore = 0;

  // Is the game over?
  private boolean gameOver = false;


  /**
   * Method that handles KeyEvents for FlappyBird.
   *
   * @param keyEvent Int Keyevent to interpret.
   */
  public void keyMove(int keyEvent) {
    switch (keyEvent) {
      // Hop if space is pressed.
      case KeyEvent.VK_SPACE:
        if (this.p1.isAlive()) {
          if (this.p1.getPosition().getY() > 0 && this.p1.getPosition().getY() < FlappyBirdView
                  .WINDOW_HEIGHT) {
            this.p1.getVelocity().setLocation(this.p1.getVelocity().getX(), -Player
                    .ACCELERATION_JUMP);
            this.p1.getPosition().setLocation(this.p1.getPosition().getX(), this.p1.getPosition()
                    .getY() -
                    Player.ACCELERATION_JUMP);
            this.pushSound(FlappySounds.S_FLAP);
          }
        }
        break;
      // Restart game if R is pressed.
      case KeyEvent.VK_R:
        this.restartGame();

      default:
        break;
    }
  }

  /**
   * Pushes a requested FlappySound to the sound stack.
   */
  private void pushSound(FlappySounds flappySound) {
    this.soundsStack.push(flappySound);
  }

  /**
   * Restarts the game of Flappy Bird.
   */
  public void restartGame() {
    this.p1 = new Player((int) FlappyBirdField.FIELD_DIM.getWidth() / 2, (int)
            FlappyBirdField.FIELD_DIM.getHeight() / 2);
    this.columns = new ArrayList<Column>();
    this.score = 0;
  }

  /**
   * Manages all methods that must be called every tick to update the game state.
   */
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

  /**
   * Creates a new column every 55 ticks.
   */
  private void createColumn(int tickCount) {
    if (tickCount % 55 == 0) {
      this.columns.add(new Column((int) FlappyBirdField.FIELD_DIM.getWidth() + Player.RADIUS,
              0));
    }
  }

  /**
   * Transitions all columns to the left
   */
  private void moveColumns() {
    for (Column c : this.columns) {
      c.getPosition().setLocation(c.getPosition().getX() - 4.5, c.getPosition().getY());
    }
  }

  /**
   * Removes columns that have gone offscreen on the left
   */
  private void removeColumns() {
    if (this.columns.size() > 1) {
      if (this.columns.get(0).getPosition().getX() < 0) {
        this.columns.remove(0);
      }
    }
  }

  /**
   * Updates the current score. If the score passes the high score, high score is updated as well.
   */
  private void countScore() {
    for (Column c : this.columns) {
      if (!c.isPassed() && p1.hasPassedColumn(c)) {
        this.score++;
        if (this.score >= this.highScore) {
          this.highScore = this.score;
        }
        this.pushSound(FlappySounds.S_SCORE);
      }
    }
  }


  /**
   * On tick, checks if the player has collided with any column or the ground.
   */
  private void checkCollisions() {
    boolean collision = false;

    // Check if we hit the ground
    if (p1.getPosition().getY() >= FlappyBirdView.WINDOW_HEIGHT - Player.RADIUS * 3) {
      collision = true;
    }

    // Check if we hit a column.
    for (Column c : this.columns) {
      if (!collision && !c.isPassed() && p1.isAlive() && this.columnCollisionCheck(this.p1, c)) {
        collision = true;
        c.collision();
      }
    }

    if (collision) {
      p1.kill();
      this.pushSound(FlappySounds.S_DEATH);
    }
  }

  /**
   * Checks if the given player has collided with a given column
   *
   * @param p The given player.
   * @param c The given column.
   * @return Whether the player has collided with the column.
   */
  private boolean columnCollisionCheck(Player p, Column c) {
    // collisions for columns with upward offset

    if (!c.isPassed() && !c.isCollided() && (Math.abs(p.getPosition().getX() - c.getPosition()
            .getX()) < Player.RADIUS * 2)) {
      return p.getPosition().getY() > c.getYOffset() + c.getGapHeight() || p.getPosition().getY() <
              c.getYOffset();
    }
    // collision for when the player hits the ground out zone (slightly above the ground)
    return false;
  }

  /**
   * Getting for the player.
   *
   * @return The player.
   */
  public Player getPlayer() {
    return this.p1;
  }

  /**
   * Getting for the ArrayList of all columns.
   *
   * @return ArrayList of all columns.
   */
  public ArrayList<Column> getColumns() {
    return this.columns;
  }

  /**
   * Getter for the stack of FlappySounds.
   *
   * @return The sound stack.
   */
  public Stack<FlappySounds> getSoundsStack() {
    return soundsStack;
  }

  /**
   * Getter for the game's current score.
   *
   * @return The current score.
   */
  public int getScore() {
    return score;
  }

  /**
   * Getter for the game's current high score.
   *
   * @return The current high score.
   */
  public int getHighScore() {
    return highScore;
  }


  /**
   * Checks if the game is over.
   *
   * @return If the game is over.
   */
  public boolean isGameOver() {
    return this.gameOver;
  }
}
