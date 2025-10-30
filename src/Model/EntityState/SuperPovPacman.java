package Model.EntityState;


import Engine.Game;
import Model.Ghost;
import Model.Pacman;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class SuperPovPacman implements EntityState {
    private Game game;
    private Pacman pacman;
    private Image ghost = new ImageIcon("images/ghost_left.png").getImage();

    public SuperPovPacman(Game game, Pacman pacman) {
        this.game = game;
        this.pacman = pacman;
    }

    @Override
    public void colorState() {
        pacman.setColor(Color.ORANGE);
    }

    @Override
    public void state() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Future<?> future = executor.submit(() -> {
            game.getP().getTimerGhost().setDelay(60);
            long start = System.currentTimeMillis();
            for (Ghost g : game.getGhosts()) {
                g.setState(new AfraidGhost(g));
                g.state();
            }
            while (!Thread.interrupted()) {
                colorState();
                pacman.setSuperPow(true);
                pacman.setSuperPowTimer(System.currentTimeMillis() - start);
            }
            game.getP().getTimerGhost().setDelay(20);
            pacman.setSuperPow(false);
            pacman.setSuperPowTimer(0);

            pacman.setState(new NormalPacman(pacman));
            pacman.getState().state();

            for (Ghost g : game.getGhosts()) {
                g.setState(g.getNormalState());
                g.state();
                g.setImage(ghost);
            }
        });
        executor.schedule(() -> {
            future.cancel(true);
        }, 6, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
