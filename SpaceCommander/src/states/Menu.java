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
    }
}
