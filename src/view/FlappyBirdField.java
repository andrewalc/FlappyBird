package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Column;
import model.GameState;
import model.Player;

/**
 * Created by Andrew Alcala on 11/7/2017.
 */
public class FlappyBirdField extends JPanel {
  private GameState state;
  public static final Dimension FIELD_DIM = new Dimension(FlappyBirdView.WINDOW_WIDTH,
          FlappyBirdView.WINDOW_HEIGHT);
  private Font scoreFont = new Font("Ariel", Font.PLAIN, 40);
  private Font highScoreFont = new Font("Ariel", Font.BOLD, 15);

  private Font msgFont = new Font("Ariel", Font.PLAIN, 30);

  Image bgImage = new ImageIcon("background.jpg").getImage().getScaledInstance((int)
                  FlappyBirdField.FIELD_DIM.getWidth(),
          (int) FlappyBirdField.FIELD_DIM.getHeight(), Image.SCALE_DEFAULT);
  Image playerImage = new ImageIcon("flappy_bird.png").getImage();


  public FlappyBirdField() {
    setVisible(true);
    setPreferredSize(FIELD_DIM);
    setOpaque(false);
  }

  public void updateState(GameState state) {
    this.state = state;
    repaint();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBackgroundImage(g);
    drawColumns(g);
    drawScore(g);
    drawPlayer(g);
    drawMessages(g);
  }

  private void drawMessages(Graphics g) {
    if (!state.getPlayer().isAlive()) {

      g.setColor(Color.BLACK);
      g.fillRect(0, (int) FIELD_DIM.getHeight() / 4 - msgFont.getSize(), (int) FIELD_DIM.getWidth
              (), msgFont.getSize() + 10);
      g.setColor(Color.RED);
      g.setFont(msgFont);
      g.drawString("Press R to try again.", 100,
              (int) FIELD_DIM.getHeight() / 4);
    }
  }

  private void drawBackgroundImage(Graphics g) {
    g.drawImage(bgImage, 0, 0, null);
  }

  private void drawScore(Graphics g) {
    g.setColor(Color.YELLOW);
    g.setFont(scoreFont);
    g.drawString("" + state.getScore(), (int) FIELD_DIM.getWidth() / 2, 100);
    g.setFont(highScoreFont);
    g.drawString("High Score: " + state.getHighScore(), 10, 20);
  }

  private void drawColumns(Graphics g) {
    for (Column c : state.getColumns()) {
      g.setColor(Color.BLACK);

      if (c.isPassed()) {
        g.setColor(Color.GREEN);
      }

      if (c.isCollided()) {
        g.setColor(Color.RED);
      }
      // Draws the full column
      g.fillRect((int) c.getPosition().getX(), (int) c.getPosition().getY(), (int) Column.WIDTH,
              (int) FlappyBirdField.FIELD_DIM.getHeight());
      // Draws the gap
      g.setColor(Color.GREEN);
      g.fillRect((int) c.getPosition().getX(), (int) c.getYOffset(), (int) Column.WIDTH,
              c.getGapHeight());
    }

  }

  private void drawPlayer(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    // Save the current orientation of the world.
    AffineTransform oldTransform = g2d.getTransform();

    // Rotate the world according to the velocity, then draw on this rotated world.
    g2d.rotate(Math.toRadians(state.getPlayer().getVelocity().getY()) * 1.5,
            state.getPlayer().getPosition().getX(), state.getPlayer().getPosition().getY());

    // Draws shape where player hitbox is accurate.
//    g.setColor(Color.GREEN);
//    g.fillOval((int) state.getPlayer().getPosition().getX() - Player.RADIUS, (int) state
//            .getPlayer().getPosition().getY() - Player.RADIUS, Player.RADIUS * 2, Player.RADIUS *
//            2);

    g.drawImage(playerImage, (int) state.getPlayer().getPosition().getX() - Player.RADIUS * 3,
            (int) state.getPlayer().getPosition().getY() - Player.RADIUS * 3, null);

    // Origin point
//    g.setColor(Color.CYAN);
//    g.drawRect((int) state.getPlayer().getPosition().getX(), (int) state.getPlayer().getPosition
//            ().getY(), 2, 2);

    // Undo the rotate
    g2d.setTransform(oldTransform);


  }


}
