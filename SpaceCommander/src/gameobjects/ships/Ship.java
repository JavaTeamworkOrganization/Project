package gameobjects.ships;

import contracts.Projectile;
import gameobjects.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship extends Entity implements contracts.Ship {
    private int health;
    protected ArrayList<Projectile> projectiles;


    public Ship(int x, int y, int width, int height, int velocity, int health) {
        super(x, y, width, height, velocity);
        this.health = health;
        this.projectiles = new ArrayList<>();
    }

    protected void addProjectile(Projectile projectile) {
        this.projectiles.add(projectile);
    }

    @Override
    public List<Projectile> getProjectiles() {
        return this.projectiles;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int getHealth) {
        this.health = 0;
    }
}
