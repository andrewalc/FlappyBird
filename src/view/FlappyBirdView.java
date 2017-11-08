package view;

/**
 * Created by Andrew Alcala on 11/7/2017.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import model.GameState;

public class FlappyBirdView extends JFrame {
  public static final int WINDOW_WIDTH = 500;
  public static final int WINDOW_HEIGHT = 700;
  public static final Dimension WINDOW_DIM = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
  public static final Color COLOR_BACKGROUND = Color.BLACK;
  private FlappyBirdField fieldPanel;
  private SoundManager soundManager = new SoundManager();

  public FlappyBirdView() {
    super("Flappy Bird");
    // JFrame Settings
    setSize(WINDOW_DIM);
    setPreferredSize(WINDOW_DIM);
    setResizable(false);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setFocusable(true);
    requestFocus();

    // Put all things in here
    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.setBackground(COLOR_BACKGROUND);

    fieldPanel = new FlappyBirdField();
    contentPane.add(fieldPanel, BorderLayout.CENTER);
    // Set and pack
    setContentPane(contentPane);
    pack();
  }

  public void initialize() {
    setVisible(true);
  }

  public void updateView(GameState state, int tickCount) {

    fieldPanel.updateState(state);
    soundManager.manageStack(state.getSoundsStack());
  }
}
