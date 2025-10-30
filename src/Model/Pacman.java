package Model;

import Engine.Game;
import Model.EntityState.NormalPacman;

import java.awt.Color;

public class Pacman extends Entity {
    private Game game;
    private int life;
    private boolean alive;
    private boolean invisible;
    private long invisibleTimer;
    private boolean superPow;
    private long superPowTimer;
    private boolean lifeTake;

    public Pacman(Game game) {
        super(8 * 36, 11 * 36, Direction.VOID, Color.decode("#fdff00"), new NormalPacman());
        this.game = game;
        this.life = 3;
        this.alive = true;
        this.invisible = false;
        this.invisibleTimer = 0;
        this.superPow = false;
        this.superPowTimer = 0;
        this.lifeTake = false;
        setState(new NormalPacman(this));
    }

    public int getLife() {
        return life;
    }

    public void oneUp() {
        this.life++;
    }

    public boolean checkCollision(Ghost ghost) {
        int pacX = getX();
        int pacY = getY();
        int gX = ghost.getX();
        int gY = ghost.getY();

        if (!invisible || superPow) {
            if ((gY == pacY && gX + 35 >= pacX && gX + 35 <= pacX + 35) || // Right
                    (gX == pacX && gY >= pacY && gY <= pacY + 35) || // up
                    (gX == pacX && gY + 35 >= pacY && gY + 35 <= pacY + 35) || // down
                    (gY == pacY && gX >= pacX && gX <= pacX + 35) // Left
            ) {
                if (superPow) {
                    superPow(ghost);
                    return false;
                } else {
                    if (Music.sound)
                        Music.play_music(2);
                    life--;
                    setX(8 * 36);
                    setY(11 * 36);
                    for (Ghost g : game.getGhosts()) {
                        g.setX(36 * 8);
                        g.setY(36 * 7);
                    }

                    if (life <= 0) {
                        setAlive(false);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public long getInvisibleTimer() {
        return invisibleTimer;
    }

    public void setInvisibleTimer(long invisibleTimer) {
        this.invisibleTimer = invisibleTimer;
    }

    public boolean isSuperPow() {
        return superPow;
    }

    public void setSuperPow(boolean superPow) {
        this.superPow = superPow;
    }

    public long getSuperPowTimer() {
        return superPowTimer;
    }

    public void setSuperPowTimer(long superPowTimer) {
        this.superPowTimer = superPowTimer;
    }

    public void superPow(Ghost g) {
        g.setX(36 * 8);
        g.setY(36 * 7);
        if (Music.sound)
            Music.play_music(4);
    }

    public void eatMix() {
        game.getMap().setBlockingWall(!game.getMap().isBlockingWall());
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isLifeTake() {
        return lifeTake;
    }

    public void setLifeTake(boolean lifeTake) {
        this.lifeTake = lifeTake;
    }
}
