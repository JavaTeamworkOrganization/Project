package states;

import game.GameEngine;
import gameobjects.ships.Player;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;

public class GameState extends State {
    private boolean hasToRender = true;
    private SpriteSheet backGround = new SpriteSheet(ImageLoader.loadImage("/bkg.jpg"));
    private int backGroundVelocity = 5;
    private int backGroundY = 600;
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight());
    private int x;
    private int y;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void tick() {


        if (y == 7) {
            y = 0;
            x = 0;
            this.hasToRender = true;
        }
        this.player.move(this.gameEngine.inputHandler);
        this.player.tick();
        this.backGroundY -= this.backGroundVelocity;
        if (this.backGroundY < 0) {
            this.backGroundY = 600;
        }

        if (this.player.getProjectiles().size() >= 1) {
            for (int index = 0; index < this.player.getProjectiles().size(); index++) {
                if (this.player.getProjectiles().get(index).getY() <= 0) {
                    this.player.getProjectiles().remove(index);
                }
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(backGround.crop(0, this.backGroundY, 800, 600), 0, 0, 800, 600, null);
        if (hasToRender) {
            this.player.render(graphics);
        }
    }
}