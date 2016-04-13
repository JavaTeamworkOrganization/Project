package contracts;

import java.awt.*;

public interface GameObject extends Intersectable, Updateable {
    Rectangle getBoundingBox();
}
