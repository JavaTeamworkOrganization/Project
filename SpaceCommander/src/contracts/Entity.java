package contracts;

import java.awt.*;

public interface Entity extends Intersectable, Updateable {
    int getY();

    void setY(int y);

    int getX();

    void setX(int x);

    int getWidth();

    int getHeight();

    Rectangle getBoundingBox();
}
