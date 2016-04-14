package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.Bullet;
import gfx.Assets;
import input.InputHandler;

import java.awt.*;

public class Player extends Ship {
    private static final int WIDHT = 100;
    private static final int HEIGHT = 100;
    private static final int VELOCITY = 5;
    private static final int DEFAULT_HEALTH = 100;

    private boolean hasShot = false;
    private int now;
    private int lastPressed;

    private boolean isShooting;

    public Player(int x, int y) {
        super(x, y, WIDHT, HEIGHT, VELOCITY, DEFAULT_HEALTH);
        this.isShooting = true;
    }

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
        graphics.drawImage(Assets.playerSpriteSheet.crop(0, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }
    }

    public void move(InputHandler inputHandler) {
        if (inputHandler.up) {
            this.y -= this.getVelocity() * 2;
        } else if(inputHandler.down) {
            this.y += this.getVelocity();
        } else if(inputHandler.left) {
            this.x -= this.getVelocity();
        } else if(inputHandler.right) {
            this.x += this.getVelocity();
        }

        if (inputHandler.spacebar) {
            this.now++;
            if (!this.hasShot) {

                System.out.println(now - lastPressed);
                lastPressed = now;
                this.shoot();
                this.hasShot = true;
            }
        } else {
            this.hasShot = false;
        }
    }

    private void shoot() {
        this.addProjectile(new Bullet(this.getX() + 43, this.getY()));
    }
}
