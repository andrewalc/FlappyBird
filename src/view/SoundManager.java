package view;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import model.FlappySounds;

/**
 * Sound manage for Flappy Bird.
 */
public class SoundManager {
  SoundManager() {
  }

  void manageStack(Stack<FlappySounds> soundStack) {
    if (!soundStack.empty()) {
      FlappySounds sound = soundStack.pop();
      switch (sound) {
        case S_FLAP:
          try {
            this.playSound("sounds/sfx_wing.wav");
          } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
          }
          break;
        case S_SCORE:
          try {
            this.playSound("sounds/sfx_point.wav");
          } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
          }
          break;
        case S_DEATH:
          try {
            this.playSound("sounds/sfx_hit.wav");
            this.playSound("sounds/sfx_die.wav");
          } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
          }
          break;

        default:
          break;
      }
    }

  }

  public static void playSound(String soundFile) throws LineUnavailableException, IOException,
          UnsupportedAudioFileException {
    File f = new File(soundFile);
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
    Clip clip = AudioSystem.getClip();
    clip.open(audioIn);
    clip.start();
  }
}
