package game;

import gfx.Assets;
import input.InputHandler;
import states.GameState;
import states.StateManager;

import java.awt.*;


public class InstructionsMenu {
    private int screenHeight = 800;
    private  int screenWidth = 600;

    public  void render(Graphics graphics,InputHandler inputHandler){
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
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        graphics.drawString("Go to main menu: Backspace", 200, 350);


    }
}
