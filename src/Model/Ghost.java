package Model;

import Model.EntityState.NormalGhost;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Ghost extends Entity {
    private final NormalGhost normalState;
    private Image image =new ImageIcon("images/ghost_left.png").getImage(); ;

    public Ghost(int x, int y, Color color) {
        super(x, y, Direction.random(), color, new NormalGhost(color, null));
        this.normalState = new NormalGhost(color, this);
        setState(this.normalState);
        this.image = this.normalState.getImage();
    }

    public NormalGhost getNormalState() {
        return normalState;
    }

    public void setImage(Image img){
        this.image=img;
    }

    public Image getImage(){
        return this.image;
    }
}
