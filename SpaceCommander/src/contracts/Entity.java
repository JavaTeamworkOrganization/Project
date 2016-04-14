package contracts;

import java.awt.*;

public interface Entity extends Intersectable, Updateable {
    int getY();

    int getX();

    Rectangle getBoundingBox();
}
