package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage gameStateBackGround;
    public static SpriteSheet playerSpriteSheet;
    public static SpriteSheet explosionSprite;

    public static void init() {
        gameStateBackGround = ImageLoader.loadImage("/bkg.jpg");
        playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/player.png"));
        explosionSprite = new SpriteSheet(ImageLoader.loadImage("/explosion.png"));
    }
}
