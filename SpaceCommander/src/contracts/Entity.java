package contracts;

import java.awt.*;

public interface Entity extends Intersectable, Updateable {
    int getY();

    int getX();

    int getWidth();

    int getHeight();

    Rectangle getBoundingBox();
}
