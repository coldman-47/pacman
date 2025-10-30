package Engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Direction;
import Model.EntityState.InvisiblePacman;
import Model.EntityState.SuperPovPacman;
import Model.Music;
import Model.Pacman;
import Model.Type;
import Views.GameView;


public class PacAction implements ActionListener {
    private Game game;
    private GameView view;
    private Pacman pacman;

    public PacAction(Game game, GameView view, Pacman pacman) {
        this.game = game;
        this.view = view;
        this.pacman = pacman;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Type[][] map = game.getMap().getMap();
        int pacXMove = pacman.getX() + pacman.getDirection().getDx();
        int pacYMove = pacman.getY() + pacman.getDirection().getDy();

        if (pacXMove == 0 && pacYMove == 252) {
            pacXMove = 572;
        }
        if (pacXMove == 576 && pacYMove == 252) {
            pacXMove = 4;
        }

        if (map[(pacYMove) / 36][(pacXMove) / 36] != Type.W &&
                map[(pacYMove) / 36][(pacXMove + 34) / 36] != Type.W &&
                map[(pacYMove + 34) / 36][(pacXMove) / 36] != Type.W &&
                map[(pacYMove + 34) / 36][(pacXMove + 34) / 36] != Type.W) {
            switch (map[(pacYMove) / 36][(pacXMove) / 36]) {
                case C:
                    game.addScore(100);
                    break;
                case I:
                    game.addScore(300);
                    pacman.setState(new InvisiblePacman(pacman));
                    pacman.getState().state();
                    if (Music.sound)
                        Music.play_music(4);
                    break;
                case S:
                    game.addScore(500);
                    pacman.setState(new SuperPovPacman(game, pacman));
                    pacman.getState().state();
                    if (Music.sound)
                        Music.play_music(4);
                    break;
                case M:
                    game.addScore(1000);
                    game.getPacman().eatMix();
                    for(int i = 0 ; i < game.getGhosts().length ; i++){
                        game.getGhosts()[i].setX(36 * 7);
                        game.getGhosts()[i].setY(36 * 5);
                    }
                    break;
                default:
                    break;
            }
            game.gainOneUp(); // Verifie s'il a 5000pts pour ajouter une vie en plus.
            game.getMap().replaceNothing(pacman.getY() / 36, pacman.getX() / 36);
            pacman.setX(pacXMove);
            pacman.setY(pacYMove);


        } else {
            if (pacman.getDirection() == Direction.UP) {
                // System.out.println("Bloqué par le mur du haut");
            } else if (pacman.getDirection() == Direction.DOWN) {
                // System.out.println("Bloqué par le mur du bas");
            } else if (pacman.getDirection() == Direction.RIGHT) {
                // System.out.println("Bloqué par le mur à droite");
            } else {
                // System.out.println("Bloqué par le mur à gauche");
            }

        }
        view.repaint(); 
    }
}
