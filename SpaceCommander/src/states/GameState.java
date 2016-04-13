package states;

import enums.PlayerMovement;
import game.GameEngine;
import gameobjects.Player;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;

public class GameState extends State {
    private boolean hasToRender = true;
    private SpriteSheet backGround = new SpriteSheet(ImageLoader.loadImage("/bkg.jpg"));
    private int backGroundVelocity = 5;
    private int backGroundY = 600;
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight() - 100, 100, 100);
    private int x;
    private int y;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void tick() {
        if (x <= 7 && (!hasToRender)) {
            x++;
        } else {
            y++;
        }

        if (this.gameEngine.inputHandler.up) {
            this.player.move(PlayerMovement.Up);
        } else if(this.gameEngine.inputHandler.down) {
            this.player.move(PlayerMovement.Down);
        } else if(this.gameEngine.inputHandler.left) {
            this.player.move(PlayerMovement.Left);
        } else if(this.gameEngine.inputHandler.right) {
            this.player.move(PlayerMovement.Right);
        }



        if (y == 7) {
            y = 0;
            x = 0;
            this.hasToRender = true;
        }

        this.player.tick();
        this.backGroundY -= this.backGroundVelocity;
        if (this.backGroundY < 0) {
            this.backGroundY = 600;
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