package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.EnemyBullet;
import gfx.Assets;

import java.awt.*;

public class EnemyShip extends Ship {
    private static final int DEFAULT_HEALTH = 30;
    private static final int DEFAULT_VELOCITY = 2;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 54;
    private static final int SHOOTING_RATIO = 30;

    private int shootingCount;
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
        this.shootingCount++;
        if (this.shootingCount >= SHOOTING_RATIO) {
            this.shoot();
            this.shootingCount = 0;
        }
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

        int currentY = this.getY() + this.velocity;
        this.setY(currentY);
        if (this.getProjectiles().size() > 1) {
            for (Projectile projectile : projectiles) {
                projectile.tick();
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (ishit) {
            graphics.drawImage(Assets.enemyHit, this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(Assets.enemyImage, this.x, this.y, this.width, this.height, null);
        }

        if (this.getProjectiles().size() > 1) {
            for (Projectile projectile : projectiles) {
                projectile.render(graphics);
            }
        }
    }

    private void shoot() {
        this.addProjectile(new EnemyBullet(this.getX() + 18, this.getY() + 30));
    }
}