package gameobjects.ships;

import contracts.Projectile;
import gameobjects.projectiles.BlueLaser;
import gfx.Assets;
import input.InputHandler;

import java.awt.*;

public class Player extends Ship {
    private static final int WIDHT = 100;
    private static final int HEIGHT = 100;
    private static final int VELOCITY = 7;
    private static final int DEFAULT_HEALTH = 100;
    private static final int FRAMES_UNTIL_NEXT_SHOT = 40;

    private boolean hasTurnedLeft;
    private boolean hasTurnedRight;

    private int spaceDisabilityCouner;
    private boolean spaceBarHitted;
    private boolean hashShot;
    private int now;
    private int timesShot;

    private int score;

    public Player(int x, int y) {
        super(x, y, WIDHT, HEIGHT, VELOCITY, DEFAULT_HEALTH);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }


    @Override
    public void tick() {
        this.getBoundingBox().setBounds(this.x + 23, this.y + 20, this.width - 46, this.height - 40);
        this.y += this.getVelocity();
        if (this.getY() > 500) {
            this.setY(500);
        }

        drawHitImage();

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
        if(inputHandler.left) {
            this.hasTurnedLeft = true;
            this.x -= this.getVelocity();
        } else if(inputHandler.right) {
            this.hasTurnedRight = true;
            this.x += this.getVelocity();
        }

        if (inputHandler.spacebar) {
            this.spaceBarHitted = true;
        }

        if (this.spaceBarHitted && this.timesShot <= 2) {
            this.now++;
            if (this.now % 5 == 0) {
                this.timesShot++;
                this.shoot();
            }
        }

        if (this.timesShot >= 2) {
            this.hashShot = true;
        } else {
            this.hashShot = false;
        }

        if (this.hashShot && this.spaceDisabilityCouner <= FRAMES_UNTIL_NEXT_SHOT) {
            this.spaceDisabilityCouner++;
        } else if (this.spaceDisabilityCouner >= FRAMES_UNTIL_NEXT_SHOT) {
            this.timesShot = 0;
            this.spaceDisabilityCouner = 0;
            this.spaceBarHitted = false;
        }
    }

    private void shoot() {
        this.addProjectile(new BlueLaser(this.getX() + 40, this.getY() + 40));
        this.addProjectile(new BlueLaser(this.getX() + 57, this.getY() + 40));
    }
}
