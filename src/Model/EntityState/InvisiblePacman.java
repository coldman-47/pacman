package Model.EntityState;


import Model.Pacman;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InvisiblePacman implements EntityState {
    private Pacman pacman;

    public InvisiblePacman(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void colorState() {
        pacman.setColor(Color.decode("#FDFD96"));
    }

    @Override
    public void state() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        Future<?> future = executor.submit(() -> {
            colorState();
            long start = System.currentTimeMillis();
            while (!Thread.interrupted()) {
                colorState();
                pacman.setInvisible(true);
                pacman.setInvisibleTimer(System.currentTimeMillis() - start);
            }
            pacman.setInvisible(false);
            pacman.setInvisibleTimer(0);

            pacman.setState(new NormalPacman(pacman));
            pacman.getState().state();
        });
        executor.schedule(() -> {
            future.cancel(true);
        }, 10, TimeUnit.SECONDS);
        executor.shutdown();
    }

    @Override
    public String toString() {
        return "Invisible";
    }
    
}
