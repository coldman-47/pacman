package Model.EntityState;


import Model.Pacman;

import java.awt.*;

public class NormalPacman implements EntityState {
    private Pacman pacman;

    public NormalPacman() {

    }

    public NormalPacman(Pacman pacman) {
        this.pacman = pacman;
    }

    @Override
    public void colorState() {
        pacman.setColor(Color.decode("#fdff00"));
    }

    @Override
    public void state() {
        colorState();
    }
}
