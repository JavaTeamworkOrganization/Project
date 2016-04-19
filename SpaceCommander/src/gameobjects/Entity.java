package gameobjects;

import java.awt.*;

public abstract class Entity implements contracts.Entity {
    protected int x, y;
    protected int width, height;
    protected Rectangle boundingBox;
    protected int velocity;

    public Entity(int x, int y, int width, int height, int velocity) {
        this.x = x;
        this.y = y;
        this.setWidth(width);
        this.height = height;
        this.velocity = velocity;
        this.setBoundingBox(new Rectangle(this.x, this.y, this.width, this.height));
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    private void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }

    protected int getVelocity() {
        return this.velocity;
    }

    public int getX() {
        return this.x;
    }

    protected void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected  int getHeight() {
        return this.height;
    }

    protected int getWidth() {
        return this.width;
    }

    private void setHeight(int height) {
        this.height = height;
    }

    private void setWidth(int widht) {
        this.width = widht;
    }

    @Override
    public boolean intersect(Rectangle boundingBox) {
        return this.boundingBox.intersects(boundingBox) ||
                boundingBox.intersects(this.boundingBox);
    }


}
