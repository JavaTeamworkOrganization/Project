package gameobjects;

import enums.PlayerMovement;
import gfx.Assets;

import java.awt.*;

public class Player extends GameObject {

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height, 5);
    }

    @Override
    public void tick() {
        this.y += this.velocity;
        if (this.y > 500) {
            this.y = 500;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.playerSpriteSheet.crop(0, 0, this.width, this.height), this.x, this.y, this.width, this.height, null);
    }

    public void move(PlayerMovement movement) {
        switch (movement) {
            case Up:
                this.y -= velocity * 2;
                break;
            case Down:
                this.y += velocity;

                break;
            case Left:
                this.x -= this.velocity;
                break;
            case Right:
                this.x += this.velocity;
        }
    }
}
