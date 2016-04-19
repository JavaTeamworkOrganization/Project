package gameobjects.ships;

import gfx.Assets;

import java.awt.*;

public class EnemyShip extends Ship {
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_VELOCITY = 3;
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 44;

    private boolean isAlive = true;

    public EnemyShip(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VELOCITY, DEFAULT_HEALTH);

    }

    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        if (this.getHealth() <= 0) {
            isAlive = false;
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (isAlive) {
            graphics.drawImage(Assets.enemyImage, this.x, this.y, this.width, this.height, null);
        }

        graphics.drawString(String.format("%d", this.getHealth()), this.getX(), this.getY() + 50);
    }
}