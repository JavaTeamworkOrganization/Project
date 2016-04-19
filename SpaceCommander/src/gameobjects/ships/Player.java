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
    private boolean hasTurnedLeft;
    private boolean hasTurnedRight;

    public int turnLeft = 0;
    public int turnRight = 0;

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
        if (this.hasTurnedLeft) {
            graphics.drawImage(Assets.playerSpriteSheet.crop(100, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
        } else if (this.hasTurnedRight) {
            graphics.drawImage(Assets.playerSpriteSheet.crop(100, 100, this.getWidth(), this.height), this.x, this.y, this.width, this.height, null);
        } else {
            graphics.drawImage(Assets.playerSpriteSheet.crop(0, 0, 100, 100), this.x, this.y, this.width, this.height, null);
        }


        for (Projectile projectile : projectiles) {
            projectile.render(graphics);
        }
    }

    public void move(InputHandler inputHandler) {
        this.hasTurnedLeft = false;
        this.hasTurnedRight = false;
        if (inputHandler.up) {
            this.y -= this.getVelocity() * 2;
        } else if(inputHandler.down) {
            this.y += this.getVelocity();
        }
        if(inputHandler.left) {
            this.x -= this.getVelocity();
            this.hasTurnedLeft = true;
            this.hasTurnedRight = false;
            turnLeft = 100;

        } else if(inputHandler.right) {
            this.x += this.getVelocity();
            this.hasTurnedRight = true;
            this.hasTurnedLeft = false;
            turnLeft = 100;
            turnRight = 100;
        }else{
            turnLeft = 0;
            turnRight = 0;
        }

        this.now++;
        if (inputHandler.spacebar) {

//            if (this.now % 20 == 0) {
//                this.shoot();
//            }
            if (!this.hasShot) {

                System.out.println(now - lastPressed);
                lastPressed = now;
                this.shoot();
                this.hasShot = true;
            }
       } else {
           this.hasShot = false;
       }
//        } else {
//            this.now = 0;
//        }
    }

    private void shoot() {
        this.addProjectile(new Bullet(this.getX() + 43, this.getY()));
    }
}
