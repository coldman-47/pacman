package Engine;

import Model.Direction;
import Model.Type;
import Views.GameView;
import com.sun.tools.javac.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class PacMove implements KeyListener {
    private Game game;
    private GameView view;

    public PacMove(Game game, GameView view) {
        this.game = game;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Type[][] map = game.getMap().getMap();
        int pacXMove = game.getPacman().getX() + game.getPacman().getDirection().getDx();
        int pacYMove = game.getPacman().getY() + game.getPacman().getDirection().getDy();
        Direction oldDirection = game.getPacman().getDirection();

        if (pacXMove == 0 && pacYMove == 252) {
            pacXMove = 572;
        	
        }
        if (pacXMove == 576 && pacYMove == 252) {
            pacXMove = 4;
        }
       
        if (!game.win() || game.getPacman().isAlive()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    view.setAngle(Math.PI);
                    oldDirection = game.getPacman().getDirection();
                    game.getPacman().setDirection(Direction.LEFT);
                    pacYMove = game.getPacman().getY() + game.getPacman().getDirection().getDy();
                    if(pacYMove % 36 <= 18 && oldDirection == Direction.UP) {
                        pacYMove -= pacYMove % 36;
                        if(map[(pacYMove) / 36][(pacXMove - 1) / 36] != Type.W){
                            game.getPacman().setY(pacYMove);
                        }
                        else {
                            game.getPacman().setDirection(oldDirection);
                        }
                    } 
                    else if((pacYMove % 36 >= 18 || pacYMove % 36 == 0) && oldDirection == Direction.DOWN){
                        if(pacYMove % 36 == 0 && map[(pacYMove) / 36][(pacXMove - 1) / 36] != Type.W){
                        }
                        else if(map[(pacYMove + 36 - pacXMove % 36) / 36][(pacXMove - 1) / 36] != Type.W){
                            pacYMove += 36 - pacYMove % 36;
                            game.getPacman().setY(pacYMove);
                        }
                        else{
                            game.getPacman().setDirection(oldDirection);
                        }
                    }
                    else if (oldDirection == Direction.RIGHT || oldDirection == Direction.VOID){
                    }
                    else {
                        game.getPacman().setDirection(oldDirection);
                    }
                    break;

                case KeyEvent.VK_RIGHT:
                    view.setAngle(0);
                    oldDirection = game.getPacman().getDirection();
                    game.getPacman().setDirection(Direction.RIGHT);
                    pacYMove = game.getPacman().getY() + game.getPacman().getDirection().getDy();
                    if(pacYMove % 36 <= 18 && oldDirection == Direction.UP){
                        pacYMove -= pacYMove % 36;
                        if(map[(pacYMove) / 36][(pacXMove + 37) / 36] != Type.W){
                            game.getPacman().setY(pacYMove);
                        }
                        else {
                            game.getPacman().setDirection(oldDirection);
                        }
                    }
                    else if ((pacYMove % 36 >= 18 || pacYMove % 36 == 0) && oldDirection == Direction.DOWN){
                        if(pacYMove % 36 == 0 && map[(pacYMove) / 36][(pacXMove + 37) / 36] != Type.W){
                        }
                        else if(map[(pacYMove + 36 - pacXMove % 36) / 36][(pacXMove + 37) / 36] != Type.W){
                            pacYMove += 36 - pacYMove % 36;
                            game.getPacman().setY(pacYMove);
                        }
                        else{
                            game.getPacman().setDirection(oldDirection);
                        }
                    }
                    else if (oldDirection == Direction.LEFT || oldDirection == Direction.VOID){
                    }
                    else {
                        game.getPacman().setDirection(oldDirection);
                    }
                    break;

                case KeyEvent.VK_UP:
                    view.setAngle(-Math.PI/2);
                    oldDirection = game.getPacman().getDirection();
                    game.getPacman().setDirection(Direction.UP);
                    pacXMove = game.getPacman().getX() + game.getPacman().getDirection().getDx();
                    if(pacXMove % 36 <= 18 && oldDirection == Direction.LEFT){
                        pacXMove -= pacXMove % 36;
                        if(map[(pacYMove - 1) / 36][(pacXMove) / 36] != Type.W){
                            game.getPacman().setX(pacXMove);
                        }
                        else {
                            game.getPacman().setDirection(oldDirection);
                        }
                    } 
                    else if ((pacXMove % 36 >= 18 || pacXMove % 36 == 0) && oldDirection == Direction.RIGHT){
                        if(pacXMove % 36 == 0 && map[(pacYMove - 1) / 36][(pacXMove) / 36] != Type.W){
                        }
                        else if(map[(pacYMove - 1) / 36][(pacXMove + 36 - pacXMove % 36) / 36] != Type.W){
                            pacXMove += 36 - pacXMove % 36;
                            game.getPacman().setX(pacXMove);
                        }
                        else{
                            game.getPacman().setDirection(oldDirection);
                        }
                    }
                    else if (oldDirection == Direction.DOWN){
                    }
                    else {
                        game.getPacman().setDirection(oldDirection);
                    }
                    break;

                case KeyEvent.VK_DOWN:
                    view.setAngle(Math.PI/2);
                    oldDirection = game.getPacman().getDirection();
                    game.getPacman().setDirection(Direction.DOWN);
                    pacXMove = game.getPacman().getX() + game.getPacman().getDirection().getDx();
                    if(pacXMove % 36 <= 18 && oldDirection == Direction.LEFT){
                        if(map[(pacYMove + 36) / 36][(pacXMove) / 36] == Type.W){
                            game.getPacman().setDirection(oldDirection);
                        }
                        pacXMove -= pacXMove % 36;
                        if(map[(pacYMove + 36) / 36][(pacXMove) / 36] != Type.W){
                            game.getPacman().setX(pacXMove);
                        }
                    }
                    else if ((pacXMove % 36 >= 18 || pacXMove % 36 == 0) && oldDirection == Direction.RIGHT){
                        if(pacXMove % 36 == 0 && map[(pacYMove + 37) / 36][(pacXMove) / 36] != Type.W){
                        }
                        else if(map[(pacYMove + 37) / 36][(pacXMove + 36 - pacXMove % 36) / 36] != Type.W){
                            pacXMove += 36 - pacXMove % 36;
                            game.getPacman().setX(pacXMove);
                        }
                        else{
                            game.getPacman().setDirection(oldDirection);
                        }
                    }
                    else if (oldDirection == Direction.UP){
                    }
                    else {
                        game.getPacman().setDirection(oldDirection);
                    }
                    break;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (game.getPacman().isAlive()) {
                view.setStart(true);
            } else {
                view.getFrame().dispose();
                try {
                    Main.main(null);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (game.win()) {
                view.getFrame().dispose();
                try {
                    Main.main(null);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        view.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
