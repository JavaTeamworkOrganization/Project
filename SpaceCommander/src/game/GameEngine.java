package game;

import contracts.Updateable;
import gfx.Assets;
import input.InputHandler;
import states.*;
import states.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameEngine implements Runnable, Updateable {

    public static final int DEFAULT_GAME_WIDTH = 800;
    public static final int DEFAULT_GAME_HEIGHT = 600;

    private Thread mainThread;
    private boolean isRunning;

    private String gameTitle;
    private int gameWidth;
    private int gameHeight;
    private GameWindow display;
    public static InputHandler inputHandler;

    private Graphics graphics;
    private BufferStrategy bs;

    private boolean isPaused;

    //States
    private State gameState;

    public GameEngine (String title) {
        this.gameTitle = title;
        this.setGameWidth(DEFAULT_GAME_WIDTH);
        this.setGameHeight(DEFAULT_GAME_HEIGHT);
        this.isPaused = false;
    }

    private void init() {
        //init JFrame and Canvas
        this.display = new GameWindow(this.gameTitle, this.getGameWidth(), this.getGameHeight());

        this.display.getCanvas().createBufferStrategy(2);
        this.bs = this.display.getCanvas().getBufferStrategy();

        inputHandler = new InputHandler(this.display.getFrame());

        //init States
        this.gameState = new Menu(this);
        StateManager.setState(this.gameState);

        //init Assets
        Assets.init();
    }

    @Override
    public void tick() {
        if (inputHandler.escape && !this.isPaused) {
            this.isPaused = true;
        } else if (inputHandler.escape) {
            this.isPaused = false;
        }

        if (StateManager.getState() != null) {
            StateManager.getState().tick();
        }
    }

    @Override
    public void render(Graphics g) {
        g = this.bs.getDrawGraphics();
        g.clearRect(0, 0, this.getGameWidth(), this.getGameHeight());
        //Start Draw

        if (StateManager.getState() != null && !this.isPaused) {
            StateManager.getState().render(g);
        }

        //End Draw
        this.bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        this.init();
        int fps = 60;
        double timePerTick = 1_000_000_000.0 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();

        while (isRunning) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                this.render(this.graphics);
                this.tick();
                delta -= 1;
            }

        }

        this.stop();
    }

    //Removed synchronized because there is no other thread that
    //can call the start method of the main thread
    public void start() {
        if (this.isRunning) {
            return;
        }

        this.isRunning = true;

        this.mainThread = new Thread(this);
        this.mainThread.start();
    }

    //Removed synchronized because there is no other thread that
    //can call the stop method of the main thread
    public void stop() {
        if (!this.isRunning) {
            return;
        }
        this.isRunning = false;

        try {
            this.mainThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getGameWidth() {
        return this.gameWidth;
    }

    public void setGameWidth(int gameWidth) {
        this.gameWidth = gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;
    }

    public void setGameHeight(int gameHeight) {
        this.gameHeight = gameHeight;
    }
}
