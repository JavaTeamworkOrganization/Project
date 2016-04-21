package gfx;

import java.awt.image.BufferedImage;

public class Assets {
    public static SpriteSheet gameStateBackGround;
    public static SpriteSheet playerSpriteSheet;
    public static SpriteSheet explosionSprite;
    public static BufferedImage bulletImage;
    public static BufferedImage enemyBulletImage;
    public static BufferedImage enemyImage;
    public static BufferedImage gameMenuBackground;
    public static BufferedImage enemyHit;
    public static BufferedImage enemyLaser;
    public static BufferedImage enemyFighter;
    public static BufferedImage blueLaser;
    public static BufferedImage Title;

    public static void init() {
        gameStateBackGround = new SpriteSheet(ImageLoader.loadImage("/bkg.jpg"));
        playerSpriteSheet = new SpriteSheet(ImageLoader.loadImage("/player.png"));
        explosionSprite = new SpriteSheet(ImageLoader.loadImage("/explosion.png"));
        bulletImage = ImageLoader.loadImage("/bullet.gif");
        enemyBulletImage = ImageLoader.loadImage("/enemy_bullet.gif");
        enemyImage = ImageLoader.loadImage("/enemy.png");
        gameMenuBackground = ImageLoader.loadImage("/gameMenuBkg.jpg");
        enemyHit = ImageLoader.loadImage("/enemyDamaged.png");
        enemyLaser = ImageLoader.loadImage("/laser.png");
        enemyFighter = ImageLoader.loadImage("/fighter.png");
        blueLaser = ImageLoader.loadImage("/blueLaser.png");
        Title = ImageLoader.loadImage("/Background.png");
    }
}