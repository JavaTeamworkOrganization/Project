package contracts;

import java.awt.*;

public interface Updateable {
    public abstract void tick();

    public abstract void render(Graphics graphics);
}
