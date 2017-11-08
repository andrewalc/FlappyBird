package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

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
    drawPlayer(g);
    drawColumns(g);
    drawScore(g);
    g.drawRect(0, 0, this.getPreferredSize().width - 1, this.getPreferredSize().height);
  }

  private void drawScore(Graphics g) {
    g.setColor(Color.YELLOW);
    g.setFont(scoreFont);
    g.drawString("" + state.getScore(), (int) FIELD_DIM.getWidth() / 2, 100);
  }

  private void drawColumns(Graphics g) {
    for (Column c : state.getColumns()) {
      g.setColor(Color.PINK);
      if (c.isCollided()) {
        g.setColor(Color.RED);
      }
      if (c.isPassed()) {
        g.setColor(Color.GREEN);
      }
      // Draws the full column
      g.drawRect((int) c.getPosition().getX(), (int) c.getPosition().getY(), (int) Column.WIDTH,
              (int) FlappyBirdField.FIELD_DIM.getHeight());
      // Draws the gap
      g.setColor(Color.GREEN);
      g.drawRect((int) c.getPosition().getX(), (int) c.getYOffset(), (int) Column.WIDTH,
              c.getGapHeight());
    }

  }

  private void drawPlayer(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    AffineTransform oldTransform = g2d.getTransform();
    g2d.rotate(Math.toRadians(state.getPlayer().getVelocity().getY()
    ) * 1.5, state.getPlayer().getPosition().getX(), state.getPlayer().getPosition().getY());

    g.setColor(Color.ORANGE);
    g.drawRect((int) state.getPlayer().getPosition().getX(), (int) state.getPlayer().getPosition
            ().getY(), Player.RADIUS * 2, 1);
    g.setColor(Color.CYAN);
    g.drawRect((int) state.getPlayer().getPosition().getX(), (int) state.getPlayer().getPosition
            ().getY(), 2, 2);
    g.setColor(Color.GREEN);
    g.drawOval((int) state.getPlayer().getPosition().getX() - Player.RADIUS, (int) state
            .getPlayer().getPosition().getY() - Player.RADIUS, Player.RADIUS * 2, Player.RADIUS *
            2);
    g2d.setTransform(oldTransform);


  }


}
