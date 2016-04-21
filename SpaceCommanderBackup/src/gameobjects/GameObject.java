package gameobjects;

import java.awt.*;

public abstract class GameObject implements contracts.GameObject {
    protected int x, y;
    protected int width, height;
    protected Rectangle boundingBox;
    protected int velocity;

    public GameObject(int x, int y, int width, int height, int velocity) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = velocity;
        this.setBoundingBox(new Rectangle(this.x, this.y, this.width, this.height));
    }

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public int getVelocity() {
        return this.velocity;
    }

    @Override
    public boolean intersect(Rectangle boundingBox) {
        return this.boundingBox.intersects(boundingBox) ||
                boundingBox.intersects(this.boundingBox);
    }

    private void setBoundingBox(Rectangle boundingBox) {
        this.boundingBox = boundingBox;
    }
}
