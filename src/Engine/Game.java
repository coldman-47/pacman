package Engine;

import Model.GameMap;
import Model.Ghost;
import Model.Music;
import Model.Pacman;
import Views.GameView;

import java.awt.*;
import java.util.Scanner;

public class Game {
    private GameMap map;
    private Pacman pacman;
    private Ghost[] ghosts;
    private GameView p;
    private PacMove pacMove;
    private int score;

    public Game(Frame f) {
        //this.setMusic();
        this.map = new GameMap();
        this.pacman = new Pacman(this);
        int size = 36;
        this.ghosts = new Ghost[] {
                new Ghost(size * 7, size * 5, Color.decode("#ea82e5")), // Blue
                new Ghost(size * 8, size * 5, Color.decode("#46bfee")), // Red
                new Ghost(size * 9, size * 5, Color.decode("#db851c")), // Purple
                new Ghost(size * 8, size * 9, Color.decode("#d03e19")) };// Orange
        this.p = new GameView(f, this);
        this.pacMove = new PacMove(this, p);
        this.score = 0;
       // if (Music.sound) Music.play_music(1); // Play the beginning music
    }

    public PacMove getPacMove() {
        return pacMove;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public GameMap getMap() {
        return map;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public Ghost[] getGhosts() {
        return ghosts;
    }

    public GameView getP() {
        return p;
    }

    public void gainOneUp() {
        if (score % 5000 == 0 && score != 0 && !pacman.isLifeTake()) {
            pacman.oneUp();
            pacman.setLifeTake(true);
        }
        if (score % 5000 != 0 && score != 0 && pacman.isLifeTake()) {
            pacman.setLifeTake(false);
        }
    }

    public boolean win() {
        return pacman.isAlive() && map.isAllPacGumAte();
    }

    public boolean setMusic() {
       banner();
        System.out.print("Activer la musique ? \n (1: Oui, 2: Non) \n >> ");
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if (choix == 1) {
            Music.sound = true;
            return true;
        } else if (choix == 2) {
            Music.sound = false;
            return false;
        } else {
            System.out.println("Veuillez entrer 1 ou 2");
            return setMusic();
        }
    }

    public void banner(){
        String mot = "\n ██████╗  █████╗  ██████╗███╗   ███╗ █████╗ ███╗   ██╗ \n ██╔══██╗██╔══██╗██╔════╝████╗ ████║██╔══██╗████╗  ██║ \n ██████╔╝███████║██║     ██╔████╔██║███████║██╔██╗ ██║ \n ██╔═══╝ ██╔══██║██║     ██║╚██╔╝██║██╔══██║██║╚██╗██║ \n ██║     ██║  ██║╚██████╗██║ ╚═╝ ██║██║  ██║██║ ╚████║ \n ╚═╝     ╚═╝  ╚═╝ ╚═════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝ \n ";
        for (int i = 0; i < mot.length(); i++){
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.print(mot.charAt(i));
        }
            
    }
}
