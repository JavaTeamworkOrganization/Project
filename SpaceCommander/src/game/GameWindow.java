package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {
    private String title;
    private Canvas canvas;
    private JFrame frame;
    private Dimension dimension;

    public GameWindow(String title, int width, int height) {
        this.title = title;
        this.dimension = new Dimension(width, height);
        this.initFrame();
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    private void initFrame() {
        this.frame = new JFrame(this.title);
        this.frame.setSize(this.dimension);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
        this.frame.setFocusable(true);

        this.initCanvas();

        this.frame.add(this.canvas);
        this.frame.pack();
    }

    private void initCanvas() {
        this.canvas = new Canvas();
        this.canvas.setPreferredSize(this.dimension);
        this.canvas.setMaximumSize(this.dimension);
        this.canvas.setMinimumSize(this.dimension);
        this.canvas.setFocusable(false);
    }
}
