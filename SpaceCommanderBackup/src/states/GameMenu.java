//package states;
//
//import game.GameEngine;
//import gfx.Assets;
//
//import java.awt.*;
//
//public class GameMenu extends State {
//    private int screenWidth  = this.gameEngine.getGameWidth();
//    private int screenHeight = this.gameEngine.getGameHeight();
//
//    private int gameTitleX;
//    private int gameTitleY;
//
//// int the main game use if isSettingsOpened with button Esc isSettingsOpen
//    private boolean isSettingsOpened    = false;
//    private boolean isBackButtonClicked = false;
//    private int buttonWidth  = 250;
//    private int buttonHeight = 50;
//
//    private int padding = 25;
//
//    private int buttonX = (screenWidth / 2) - 100;
//
//    private int startButtonY    = screenHeight - (screenHeight - ((45 * screenHeight) / 100));
//    private int settingsButtonY = startButtonY + 70;
//    private int exitButtonY     = settingsButtonY + 70;
//    private int backButtonY     = screenHeight - 100;
//
//    private Rectangle startButton    = new Rectangle(buttonX, startButtonY, buttonWidth, buttonHeight);
//    private Rectangle settingsButton = new Rectangle(buttonX, settingsButtonY, buttonWidth, buttonHeight);
//    private Rectangle exitButton     = new Rectangle(buttonX, exitButtonY, buttonWidth, buttonHeight);
//    private Rectangle backButton     = new Rectangle(buttonX, backButtonY, buttonWidth, buttonHeight);
//
//    public GameMenu(GameEngine gameEngine) {
//        super(gameEngine);
//    }
//
//    public Rectangle startButton(){
//        return startButton;
//    }
//
//    public Rectangle settingsButton(){
//        return settingsButton;
//    }
//
//    public Rectangle exitButton(){
//        return exitButton;
//    }
//
//    public Rectangle backButton(){
//        return backButton;
//    }
//
//    public void setSettingsOpened(boolean is){
//        isSettingsOpened = is;
//    }
//
//    public void setBackClicked(boolean is){
//        isBackButtonClicked = is;
//    }
//
//
//    /**
//     * Method for drawing the game menu
//     */
//    public void drawGameMenu(Graphics g){
//        if (!isSettingsOpened){
//
//            g.setColor(Color.BLACK);
//            g.fillRect(0, 0, screenWidth, screenHeight);
//            g.drawImage(Assets.gameMenuBackground, 0, 0, null);
//
//
//
//            /* draw the frame around the buttons */
//            g.setColor(new Color(71, 107, 255));
//            g.drawRect(startButton.x - 2, startButton.y - 1, startButton.width + 2, startButton.height + 2);
//            g.drawRect(settingsButton.x - 2, settingsButton.y - 1, settingsButton.width + 2, settingsButton.height + 2);
//            g.drawRect(exitButton.x - 2, exitButton.y - 1, exitButton.width + 2, exitButton.height + 2);
//
//            /* draw the buttons */
//            if (!isStartHovered){
//                g.setColor(new Color(1, 14, 22));
//            } else {
//                g.setColor(new Color(56, 14, 112, 223));
//            }
//            g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
//
//            if (!isSettingsHovered){
//                g.setColor(new Color(1, 14, 22));
//            } else {
//                g.setColor(new Color(56, 14, 112, 223));
//            }
//            g.fillRect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
//
//            if (!isExitHovered){
//                g.setColor(new Color(1, 14, 22));
//            } else {
//                g.setColor(new Color(56, 14, 112, 223));
//            }
//            g.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
//
//            /* draw names of buttons */
//            g.setFont(new Font("Arial", Font.BOLD, 26));
//            g.setColor(new Color(1, 178, 241));
//
//            g.drawString("START", buttonX + 80, startButtonY + padding);
//            g.drawString("INSTRUCTIONS", buttonX + 60, settingsButtonY + padding);
//            g.drawString("EXIT", buttonX + 95, exitButtonY + padding);
//
//        } else {  // draw SETTINGS MENU
//            if (!isBackButtonClicked){
//                g.drawImage(Assets.gameMenuBackground, 0, 0, null);
//                g.setColor(new Color(1, 14, 22));
//                int width = screenWidth - 400;
//                g.fillRect(200, 0, width, screenHeight);
//
//                if (!isBackHovered){
//                    g.setColor(new Color(1, 14, 22));
//                } else {
//                    g.setColor(new Color(56, 14, 112, 223));
//                }
//                g.fillRect(backButton.x, backButton.y, backButton.width, backButton.height);
//                // draw Controls
//                g.setColor(new Color(1, 178, 241));
//                g.setFont(new Font("Arial", Font.BOLD, 26));
//                g.drawString("PLAYER", 220, 40);
//                g.setFont(new Font("Arial", Font.BOLD, 20));
//                g.drawString("Move Forward: UP_ARROW_KEY", 220, 80);
//                g.drawString("Move Back:      DOWN_ARROW_KEY", 220, 110);
//                g.drawString("Move Right:      RIGHT_ARROW_KEY", 220, 140);
//                g.drawString("Move Left:        LEFT_ARROW_KEY", 220, 170);
//                g.drawString("Blaster:              Space", 220, 200);
//                // back to main menu button
//                g.drawRect(backButton.x - 2, backButton.y - 1, backButton.width + 2, backButton.height + 2);
//                g.setFont(new Font("Arial", Font.BOLD, 26));
//                g.drawString("BACK", buttonX + 85, backButtonY + padding);
//
//            } else { //draw back Main menu
//
//
//
//                g.setColor(Color.BLACK);
//                g.fillRect(0, 0, screenWidth, screenHeight);
//                g.drawImage(Assets.gameMenuBackground, 0, 0, null);
//
//
//                /* draw the frame around the buttons */
//                g.setColor(new Color(1, 178, 241));
//                g.drawRect(startButton.x - 2, startButton.y - 1, startButton.width + 2, startButton.height + 2);
//                g.drawRect(settingsButton.x - 2, settingsButton.y - 1, settingsButton.width + 2, settingsButton.height + 2);
//                g.drawRect(exitButton.x - 2, exitButton.y - 1, exitButton.width + 2, exitButton.height + 2);
//
//                /* draw the buttons */
//                g.setColor(new Color(1, 14, 22));
//                g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
//                g.fillRect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
//                g.fillRect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
//
//                /* draw names of buttons */
//                g.setFont(new Font("Arial", Font.BOLD, 26));
//                g.setColor(new Color(1, 178, 241));
//
//                g.drawString("START", buttonX + 80, startButtonY + padding);
//                g.drawString("SETTINGS", buttonX + 60, settingsButtonY + padding);
//                g.drawString("EXIT", buttonX + 95, exitButtonY + padding);
//
//                isBackButtonClicked = false;
//                isSettingsOpened    = false;
//            }
//        }
//    }
//
//    @Override
//    public void tick() {
//
//    }
//
//    @Override
//    public void render(Graphics graphics) {
//
//    }
//}