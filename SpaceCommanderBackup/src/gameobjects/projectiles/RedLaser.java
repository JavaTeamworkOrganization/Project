package gameobjects.projectiles;

import gfx.Assets;

import java.awt.*;

public class RedLaser extends Projectile {
    private final static int WIDTH = 4;
    private final static int HEIGHT = 20;
    private final static int VELOCITY = 10;
    private final static int DAMAGE = 6;

    public RedLaser(int x, int y) {
        super(x, y, WIDTH, HEIGHT, VELOCITY, DAMAGE);
    }

    @Override
    public void tick() {
        this.y += this.getVelocity();
        this.boundingBox.setBounds(x, y, this.width, this.height);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.enemyLaser, this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);
    }
}
