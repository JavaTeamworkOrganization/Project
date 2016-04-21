package input;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public boolean up, down, left, right, spacebar, escape;

    public InputHandler(JFrame frame) {
        frame.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.up = true;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.down = true;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            this.left = true;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.right = true;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            this.spacebar = true;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            this.escape = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_UP) {
            this.up = false;
        } else if (keyCode == KeyEvent.VK_DOWN) {
            this.down = false;
        } else if (keyCode == KeyEvent.VK_LEFT) {
            this.left = false;
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.right = false;
        } else if (keyCode == KeyEvent.VK_SPACE) {
            this.spacebar = false;
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            this.escape = false;
        }
    }
}
