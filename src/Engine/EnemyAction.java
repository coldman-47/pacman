package Engine;

import Model.Direction;
import Model.Ghost;
import Model.Type;
import Views.GameView;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

public class EnemyAction implements ActionListener {
    private Ghost[] ghosts;
    private Game game;
    private GameView p;
    private Image ghost_left = new ImageIcon("images/ghost_left.png").getImage();
    private Image ghost_right = new ImageIcon("images/ghost_right.png").getImage();
    private Image ghost_down = new ImageIcon("images/ghost_down.png").getImage();
    private Image ghost_up = new ImageIcon("images/ghost_up.png").getImage();
    private Image afraid = new ImageIcon("images/afraid.png").getImage();

    public EnemyAction(Ghost[] ghosts, Game game, GameView p) {
        this.ghosts = ghosts;
        this.game = game;
        this.p = p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Ghost g : ghosts) {
            wall(g, game.getMap().getMap());
            p.repaint();
            checkLife(g); 
        }
    }

    private void checkLife(Ghost ghost) {
        if (game.getPacman().checkCollision(ghost)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }

    // Choix de la trajectoire des fantomes dynamique :
    private void wall(Ghost g, Type[][] map) {
        int xMove = g.getX() + g.getDirection().getDx();
        int yMove = g.getY() + g.getDirection().getDy();

        if (xMove == 0 && yMove == 252) {
            xMove = 572;
        }
        if (xMove == 576 && yMove == 252) {
            xMove = 4;
        }

        while (map[(yMove) / 36][(xMove) / 36] == Type.W ||
                map[(yMove) / 36][(xMove + 34) / 36] == Type.W ||
                map[(yMove + 34) / 36][(xMove) / 36] == Type.W ||
                map[(yMove + 34) / 36][(xMove + 34) / 36] == Type.W) {

            Direction futur_direction = Direction.random();
            g.setDirection(futur_direction);
            xMove = g.getX() + g.getDirection().getDx();
            yMove = g.getY() + g.getDirection().getDy();
            swap_image(g, futur_direction);
        }

        g.setX(xMove);
        g.setY(yMove);
    }

    // cette fontion permet de swap l'image des ghosts
    private void swap_image(Ghost g, Direction futur_direction) {
        switch (futur_direction) {
            case DOWN:
                if (g.getState().toString() == "Normal") {
                    g.setImage(ghost_down);
                }else{
                    g.setImage(afraid);
                }
                break;
            case UP:
                if (g.getState().toString() == "Normal") {
                    g.setImage(ghost_up);
                }else{
                    g.setImage(afraid);
                }
                break;
            case LEFT:
                if (g.getState().toString() == "Normal") {
                    g.setImage(ghost_left);
                }else{
                    g.setImage(afraid);
                }
                break;
            case RIGHT:
                if (g.getState().toString() == "Normal") {
                    g.setImage(ghost_right);
                }else{
                    g.setImage(afraid);
                }
                break;
        }
    }
}
