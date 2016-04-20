package gameobjects;

import gfx.Assets;

import java.awt.*;

public class BackGround extends Entity {
    private int backGroundY = this.getHeight();

    public BackGround(int x, int y, int width, int height, int velocity) {
        super(x, y, width, height, velocity);
    }

    @Override
    public void tick() {
        this.backGroundY -= this.velocity;
        if (this.backGroundY < 0) {
            this.backGroundY = 600;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.gameStateBackGround.crop(0, this.backGroundY, this.width, this.height), 0, 0, this.width, this.height, null);
    }
}
