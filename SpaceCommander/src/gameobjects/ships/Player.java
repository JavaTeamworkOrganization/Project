package gameobjects.ships;

import contracts.Projectile;
import game.Score;
import gameobjects.projectiles.BlueLaser;
import gfx.Assets;
import input.InputHandler;

import java.awt.*;

public class Player extends Ship {
    private static final int WIDHT = 100;
    private static final int HEIGHT = 100;
    private static final int VELOCITY = 5;
    private static final int DEFAULT_HEALTH = 100;

    private boolean hasShot = false;
    private boolean hasTurnedLeft;
    private boolean hasTurnedRight;

    private int now;
    private int lastPressed;

    public Player(int x, int y) {
        super(x, y, WIDHT, HEIGHT, VELOCITY, DEFAULT_HEALTH);
    }

    public int playerHealth =  DEFAULT_HEALTH;

    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x + 23, this.y + 20, this.width - 46, this.height - 40);
        this.now++;
        this.y += this.getVelocity();
        if (this.getY() > 500) {
            this.setY(500);
        }
        for (Projectile projectile : projectiles) {
            projectile.tick();
        }
    }

    @Override
    public void render(Graphics graphics) {
        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }

        if (this.hasTurnedLeft) {
            graphics.drawImage(Assets.playerSpriteSheet.crop(100, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
        } else if (this.hasTurnedRight) {
            graphics.drawImage(Assets.playerSpriteSheet.crop(100, 100, this.width, this.height), this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(Assets.playerSpriteSheet.crop(0, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
        }


    }

    public void move(InputHandler inputHandler) {
        this.hasTurnedLeft = false;
        this.hasTurnedRight = false;
        if (inputHandler.up) {
            this.y -= this.getVelocity() * 2;
        } else if(inputHandler.down) {
            this.y += this.getVelocity();
        } else if(inputHandler.left) {
            this.hasTurnedLeft = true;
            this.x -= this.getVelocity();
        } else if(inputHandler.right) {
            this.hasTurnedRight = true;
            this.x += this.getVelocity();
        }

        if (inputHandler.spacebar) {
            this.now++;
            if (!this.hasShot) {
                this.shoot();
                this.hasShot = true;
            }

            if (this.now % 14 == 0) {
                System.out.println(now - lastPressed);
                lastPressed = now;
                this.shoot();
            }

        } else {
            this.now = 0;
            this.hasShot = false;
        }
    }

    private void shoot() {
        this.addProjectile(new BlueLaser(this.getX() + 40, this.getY() + 40));
        this.addProjectile(new BlueLaser(this.getX() + 57, this.getY() + 40));
    }
}
