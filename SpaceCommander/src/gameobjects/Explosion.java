package gameobjects;

import gfx.Assets;
import gfx.SpriteSheet;

import java.awt.*;

public class Explosion extends Entity{
    public boolean hasExploded;

    private SpriteSheet sprite = Assets.explosionSprite;
    private int spriteX;
    private int spriteY;

    public Explosion(int x, int y, int width, int height) {
        super(x, y, width, height, 0);
    }

    @Override
    public void tick() {
        if (spriteX <= 7) {
            spriteX++;
        } else {
            spriteY++;
        }

        if (spriteY > 7) {
            spriteX = 0;
            spriteY = 0;
            this.hasExploded = true;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(
                this.sprite.crop(spriteX * 100, spriteY * 100, 100, 100), this.getX(), this.getY(), this.getWidth(), this.getHeight(), null);

    }
}
