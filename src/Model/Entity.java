package Model;


import Model.EntityState.EntityState;

import java.awt.*;

public abstract class Entity implements EntityState {
    private int x;
    private int y;
    private Direction direction;
    private Color color;
    private EntityState state;

    public Entity(int x, int y, Direction direction, Color color, EntityState state) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.state = state;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EntityState getState() {
        return state;
    }

    public void setState(EntityState state) {
        this.state = state;
    }

    @Override
    public void colorState() {
        state.colorState();
    }

    @Override
    public void state() {
        state.state();
    }
}
