package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.RedLaser;
import gfx.Assets;

import java.awt.*;
import java.util.Random;

public class EnemyDestroyer extends EnemyShip {
    private static final int DEFAULT_HEALTH = 20;
    private static final int DEFAULT_VELOCITY = 1;
    public static final int DEFAULT_WIDTH = 40;
    public static final int DEFAULT_HEIGHT = 64;
    private static final int SHOOTING_RATE = 70;

    private int[] xVelocities;
    private Random randomXVelocityGenerator;

    public EnemyDestroyer(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VELOCITY, DEFAULT_HEALTH, SHOOTING_RATE);
        this.xVelocities = new int[] {3, 4, -4, -3};
        this.randomXVelocityGenerator = new Random();
        int xVelocityIndex = this.randomXVelocityGenerator.nextInt(4);
        this.setXVelocity(this.xVelocities[xVelocityIndex]);
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
        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }

        if (this.getIsHit() &&this.getIsAlive()) {
            graphics.drawImage(Assets.enemyFighter, this.x, this.y, this.width, this.height, null);
        } else if (this.getIsAlive()) {
            graphics.drawImage(Assets.enemyFighter, this.x, this.y, this.width, this.height, null);
        }
    }

    private void shoot() {
        this.projectiles.add(new RedLaser(this.getX() + this.getWidth() / 5, this.getY() + this.getHeight() / 2));
        this.projectiles.add(new RedLaser(this.getX() + this.getWidth() - (this.getWidth() / 5), this.getY() + this.getHeight() / 2));

    }
}