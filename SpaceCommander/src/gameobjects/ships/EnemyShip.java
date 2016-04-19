package gameobjects.ships;

import gfx.Assets;

import java.awt.*;

public class EnemyShip extends Ship {
    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_VELOCITY = 3;
    private static final int DEFAULT_WIDTH = 40;
    private static final int DEFAULT_HEIGHT = 44;

    private int hitCountDown;
    private boolean isAlive = true;
    private boolean ishit = false;

    public EnemyShip(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VELOCITY, DEFAULT_HEALTH);

    }

    public void setIsHit(boolean isHit) {
        this.ishit = isHit;
    }

    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        if (this.getHealth() <= 0) {
            isAlive = false;
        }

        if (ishit && this.hitCountDown <= 5) {
            this.ishit = true;
            this.hitCountDown++;
        } else {
            this.ishit = false;
            this.hitCountDown = 0;
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (ishit) {
            graphics.drawImage(Assets.enemyHit, this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(Assets.enemyImage, this.x, this.y, this.width, this.height, null);
        }
    }
}