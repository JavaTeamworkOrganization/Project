package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.EnemyBullet;
import gfx.Assets;

import java.awt.*;

public class RegularEnemy extends EnemyShip {
    private static final int SOORE_POINTS = 10;
    private static final int DEFAULT_HEALTH = 30;
    private static final int DEFAULT_VELOCITY = 1;
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 54;
    private static final int SHOOTING_RATE = 120;

    public RegularEnemy(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VELOCITY, DEFAULT_HEALTH, SHOOTING_RATE, SOORE_POINTS);
    }

    @Override
    public void tick() {
        this.setShootingCounter(this.getShootingCounter() + 1);
        if (this.getShootingCounter() >= SHOOTING_RATE && this.getIsAlive()) {
            this.shoot();
            this.setShootingCounter(0);
        }
        this.getBoundingBox().setBounds(this.x, this.y, this.width, this.height);
        if (this.getHealth() <= 0) {
            this.setIsAlive(false);
        }

        this.drawHitImage();

        int currentY = this.getY() + this.getVelocity();
        this.setY(currentY);
        int currentX = this.getX() + this.getXVelocity();
        this.setX(currentX);

        for (Projectile projectile : projectiles) {
            projectile.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        if (this.getIsHit() &&this.getIsAlive()) {
            graphics.drawImage(Assets.enemyHit, this.x, this.y, this.width, this.height, null);
        } else if (this.getIsAlive()) {
            graphics.drawImage(Assets.enemyImage, this.x, this.y, this.width, this.height, null);
        }

        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }

    }

    private void shoot() {
        this.addProjectile(new EnemyBullet(this.getX() + 18, this.getY() + 30));
    }
}