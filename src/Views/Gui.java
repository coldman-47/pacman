package Views;

import Engine.Game;
import Model.Type;

import javax.swing.*;

public class Gui {
    public Gui() {
        JFrame frame = new JFrame();
        Game game = new Game(frame);
        Type[][] map = game.getMap().getMap();
        
        frame.setSize(map[0].length * 36, map.length * 36 + 75);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Pacman");
        frame.add(game.getP());
        frame.addKeyListener(game.getPacMove());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        
    }
}
