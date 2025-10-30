package Model;

import java.io.File;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;

public class Music {
    public static boolean sound = true;
    final static File pacman_beginning = new File("sounds/pacman_beginning.wav");
    final static File pacman_chomp = new File("sounds/pacman_chomp.wav");
    final static File pacman_death = new File("sounds/pacman_death.wav");
    final static File pacman_eatfruit = new File("sounds/pacman_eatfruit.wav");
    final static File pacman_eatghost = new File("sounds/pacman_eatghost.wav");
    final static File pacman_extrapac = new File("sounds/pacman_extrapac.wav");

    public static void play_music(int choix) {
        try {
            AudioInputStream audioInputStream;
            Clip clip;
            switch (choix) {
                case 1:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_beginning);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    break;
                case 2:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_chomp);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                case 3:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_death);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    break;
                case 4:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_eatfruit);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                case 5:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_eatghost);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    break;
                case 6:
                    audioInputStream = AudioSystem.getAudioInputStream(pacman_extrapac);
                    clip = AudioSystem.getClip();
                    clip.open(audioInputStream);
                    clip.start();
                    break;
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error with playing sound.");
        }
    }

}
