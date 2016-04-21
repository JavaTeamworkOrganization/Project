package states;

import game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class GameOver extends State {
    private int playerSocre;

    public GameOver(GameEngine engine, int score) {
        super(engine);
        this.playerSocre = score;
    }

    @Override
    public void tick() {
        if (this.gameEngine.inputHandler.spacebar) {
            StateManager.setState(new GameState(this.gameEngine));
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.gameOver, 0, 0, this.gameEngine.getGameWidth(), this.gameEngine.getGameHeight(), null);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 50));
        graphics.drawString(String.format("Your score: %d", this.playerSocre), this.gameEngine.getGameWidth() / 2 - 180, 100);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        graphics.drawString("press SPACE to play again", this.gameEngine.getGameWidth() / 2 - 125, 500);
    }
}
