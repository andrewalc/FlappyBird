package view;

import javax.swing.JPanel;

import model.GameState;

/**
 * Created by Andrew Alcala on 11/7/2017.
 */
public class FlappyBirdField extends JPanel {
  private GameState state;

  public void updateState(GameState state) {
    this.state = state;
    repaint();
  }
}
