package Model.EntityState;


import Model.Ghost;

import java.awt.*;
import javax.swing.ImageIcon;

public class NormalGhost implements EntityState {
    private Color color;
    private Ghost ghost;
    private final Image image = new ImageIcon("images/ghost.png").getImage();

   
    public NormalGhost(Color color, Ghost ghost) {
        this.color = color;
        this.ghost = ghost;
    }

    @Override
    public void colorState() {
        ghost.setColor(color);
        this.ghost.setImage(this.image); 
    }

    @Override
    public void state() {
        colorState();
    }

    public Image getImage() {
        return this.image;
    }

    @Override
    public String toString() {
        return "Normal";
    }
    

}
