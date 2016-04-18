package contracts;

import java.util.List;

public interface Ship extends Entity, Destroyable {
    List<Projectile> getProjectiles();
}