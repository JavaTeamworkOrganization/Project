package gameobjects.ships;

import contracts.Projectile;
import gameobjects.Entity;

import java.util.ArrayList;
import java.util.List;

public abstract class Ship extends Entity implements contracts.Ship {
    private int health;
    private int hitCountDown;
    private boolean ishit;
    private boolean isAlive;
    protected ArrayList<Projectile> projectiles;


    public Ship(int x, int y, int width, int height, int velocity, int health) {
        super(x, y, width, height, velocity);
        this.health = health;
        this.projectiles = new ArrayList<>();
        this.setIsHit(false);
        this.setIsAlive(true);
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
    public void setHealth(int health) {
        this.health = health;
    }

    public void setIsHit(boolean isHit) {
        this.ishit = isHit;
    }

    protected boolean getIsHit() {
        return this.ishit;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getIsAlive() {
        return this.isAlive;
    }

    protected void setHitCountDown(int hitCountDown) {
        this.hitCountDown = hitCountDown;
    }

    protected int getHitCountDown() {
        return this.hitCountDown;
    }

    protected void drawHitImage() {
        if (this.getIsHit() && this.getHitCountDown() <= 5) {
            this.setIsHit(true);
            this.setHitCountDown(this.getHitCountDown() + 1);
        } else {
            this.setIsHit(false);
            this.setHitCountDown(0);
        }
    }
}
