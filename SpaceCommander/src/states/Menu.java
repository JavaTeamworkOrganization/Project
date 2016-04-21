package states;

import game.GameEngine;
import gfx.Assets;

import java.awt.*;

public class Menu extends State {
    private int index;
    private boolean isDownPressed = false;
    private boolean isUpPressed = false;
    private boolean isSpacePressed = false;

    private int padding = 25;
    private int startButtonY    = this.gameEngine.getGameHeight() - (this.gameEngine.getGameHeight() - ((45 * this.gameEngine.getGameHeight()) / 100));
    private int settingsButtonY = startButtonY + 70;
    private int exitButtonY     = settingsButtonY + 70;
    private int backButtonY     = 600 - 100;

    private int buttonX = (this.gameEngine.getGameWidth() / 2) - 100;

    public Menu(GameEngine engine) {
        super(engine);
    }

    @Override
    public void tick() {
        if (this.gameEngine.inputHandler.down) {
            if (!this.isDownPressed) {
                this.index++;
                this.isDownPressed = true;
            }
        } else {
            this.isDownPressed = false;
        }

        if (this.gameEngine.inputHandler.up) {
            if (!this.isUpPressed) {
                this.index--;
                this.isUpPressed = true;
            }
        } else {
            this.isUpPressed = false;
        }

        if (this.index == 0 && this.gameEngine.inputHandler.spacebar) {
            StateManager.setState(new GameState(this.gameEngine));
        }

        if (this.index < 0) {
            this.index = 0;
        } else if (this.index > 2) {
            this.index = 2;
        }
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.gameMenuBackground, 0, 0, 800, 600, null);
        graphics.setFont(new Font("Arial", Font.BOLD, 26));

        if (this.index == 0) {
            graphics.setColor(new Color(252, 255, 35));

        } else {
            graphics.setColor(new Color(255, 37, 81));
        }

        graphics.drawImage(Assets.Title, 150, 90, 550,210, null);

        graphics.drawString("START", buttonX + 80, startButtonY + padding);
        if (this.index == 1) {
            graphics.setColor(new Color(252, 255, 35));
        } else {
            graphics.setColor(new Color(255, 37, 81));
        }

        graphics.drawString("SETTINGS", buttonX + 60, settingsButtonY + padding);
        if (this.index == 2) {
            graphics.setColor(new Color(252, 255, 35));
        } else {
            graphics.setColor(new Color(255, 37, 81));
        }

        graphics.drawString("EXIT", buttonX + 95, exitButtonY + padding);

        if (this.index == 1 && GameEngine.inputHandler.spacebar) {
            graphics.drawImage(Assets.gameMenuBackground, 0, 0, 800, 600, null);
            graphics.setColor(new Color(5, 6, 22));
            graphics.fillRect(0, 0, 800, 800);

            graphics.setColor(new Color(1, 178, 241));
            graphics.setFont(new Font("Arial", Font.BOLD, 26));
            graphics.drawString("PLAYER", 220, 40);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString("Move Forward: UP_ARROW_KEY", 220, 80);
            graphics.drawString("Move Back:      DOWN_ARROW_KEY", 220, 110);
            graphics.drawString("Move Right:      RIGHT_ARROW_KEY", 220, 140);
            graphics.drawString("Move Left:        LEFT_ARROW_KEY", 220, 170);
            graphics.drawString("Blaster:            Space", 220, 200);
//            graphics.setFont(new Font("Arial", Font.BOLD, 25));
//            graphics.drawString("Go to main menu: Backspace", 200, 350);
        }
    }
}
