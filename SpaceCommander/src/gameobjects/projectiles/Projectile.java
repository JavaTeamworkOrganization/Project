package gameobjects.projectiles;

import gameobjects.Entity;

public abstract class Projectile extends Entity implements contracts.Projectile {
    private int damage;

    public Projectile(int x, int y, int width, int height, int velocity, int damage) {
        super(x, y, width, height, velocity);
        this.setDamage(damage);
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }
}
