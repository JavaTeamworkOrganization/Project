package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage gameStateBackGround;
    public static SpriteSheet playerSpriteSheet;
    public static SpriteSheet explosionSprite;
    public static BufferedImage bulletImage;
    public static BufferedImage enemyImage;
    public static BufferedImage gameMenuBackground;

    public static void init() {
        gameStateBackGround = ImageLoader.loadImage("/bkg.jpg");
        playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/player.png"));
        explosionSprite = new SpriteSheet(ImageLoader.loadImage("/explosion.png"));
        bulletImage = ImageLoader.loadImage("/bullet.gif");
        enemyImage = ImageLoader.loadImage("/enemy.png");
        gameMenuBackground = ImageLoader.loadImage("/planets.jpg");
    }
}
