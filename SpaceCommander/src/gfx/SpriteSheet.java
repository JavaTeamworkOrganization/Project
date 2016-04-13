package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage srcImage;

    public SpriteSheet(BufferedImage image) {
        this.srcImage = image;
    }

    public BufferedImage crop(int x, int y, int width, int height){
        return this.srcImage.getSubimage(x, y, width, height);
    }
}
