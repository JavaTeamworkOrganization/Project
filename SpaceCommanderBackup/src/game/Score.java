package game;


import java.awt.*;
import gameobjects.Player;

public class Score {
    private void render(Graphics graphics){
        int score = 0;
        graphics.setFont(new Font("Arial", Font.BOLD, 35));
        graphics.drawString("Score : " + score, 260, 300);
    }
}
