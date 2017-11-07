package com.bri64.Pokeman.utils;

import java.io.BufferedInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class AudioManager {

  public static void playMP3(String audioFile, boolean loop) {
    Thread audioThread = new Thread(() -> {
      try {

        Player player = new Player(
            new BufferedInputStream(AudioManager.class.getResourceAsStream(audioFile)));
        player.play();
        if (loop) {
          while (!player.isComplete()) {
            System.out.print("");
          }
          playMP3(audioFile, true);
        }
      } catch (JavaLayerException e) {
        e.printStackTrace();
      }
    });
    audioThread.start();
  }
    /*public static void playWAV(String audioFile, int repeat) {    // Deprecated, WAV's not compatible with Linux
        Thread audioThread = new Thread(()-> {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new BufferedInputStream(AudioManager.class.getResourceAsStream(audioFile))));
                clip.start();
                while (clip.isActive()) {
                    System.out.print("");
                }
                if (repeat > 0) {
                    for (int i = 0; i < repeat; i++) {
                        clip.start();
                        while (clip.isActive()) {
                            System.out.print("");
                        }
                    }
                }
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        });
        audioThread.start();
    }*/

  public static void setVolume(float ctrl) {
    try {
      Mixer.Info[] infos = AudioSystem.getMixerInfo();
      for (Mixer.Info info : infos) {
        Mixer mixer = AudioSystem.getMixer(info);
        if (mixer.isLineSupported(Port.Info.SPEAKER)) {
          Port port = (Port) mixer.getLine(Port.Info.SPEAKER);
          port.open();
          if (port.isControlSupported(FloatControl.Type.VOLUME)) {
            FloatControl volume = (FloatControl) port.getControl(FloatControl.Type.VOLUME);
            volume.setValue(ctrl);
          }
          port.close();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
