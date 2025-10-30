package Model.EntityState;

import Model.Ghost;

import java.awt.*;
import javax.swing.ImageIcon;

public class AfraidGhost implements EntityState {
    private Ghost ghost;
    private final Image image =  new ImageIcon("images/afraid.png").getImage();


    public AfraidGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    @Override
    public void colorState() {
        ghost.setColor(Color.BLUE);
        this.ghost.setImage(this.image); 
    }

    @Override
    public void state() {
        colorState();
    }

    public Image getImage(){return this.image;}

    @Override
    public String toString() {
        return "Afraid";
    }
    
}
