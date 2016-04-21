package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.RedLaser;
import gfx.Assets;

import java.awt.*;
import java.util.Random;

public class EnemyDestroyer extends EnemyShip {
    private static final int SCORE_POINTS = 10;
    private static final int DEFAULT_HEALTH = 40;
    private static final int DEFAULT_VELOCITY = 2;
    public static final int DEFAULT_WIDTH = 50;
    public static final int DEFAULT_HEIGHT = 64;
    private static final int SHOOTING_RATE = 70;

    private int[] xVelocities;
    private Random randomXVelocityGenerator;

    public EnemyDestroyer(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_VELOCITY, DEFAULT_HEALTH, SHOOTING_RATE, SCORE_POINTS);
        this.xVelocities = new int[] {2, 3, -2, -3};
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
        this.getBoundingBox().setBounds(this.x + 9, this.y, this.width - 18, this.height);
        if (this.getHealth() <= 0) {
            this.setIsAlive(false);
        }

        this.drawHitImage();

        int currentY = this.getY() + this.getVelocity();
        this.setY(currentY);
        int currentX = this.getX() + this.getXVelocity();
        this.setX(currentX);

        for (Projectile projectile : this.projectiles) {
            projectile.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }

        if (this.getIsHit() && this.getIsAlive()) {
            graphics.drawImage(Assets.damagesDestroyer, this.x, this.y, this.width, this.height, null);
        } else if (this.getIsAlive()) {
            graphics.drawImage(Assets.enemyFighter, this.x, this.y, this.width, this.height, null);
        }
    }

    private void shoot() {
        this.projectiles.add(new RedLaser(this.getX() + this.getWidth() / 5, this.getY() + this.getHeight() / 2));
        this.projectiles.add(new RedLaser(this.getX() + this.getWidth() - (this.getWidth() / 5), this.getY() + this.getHeight() / 2));

    }
}