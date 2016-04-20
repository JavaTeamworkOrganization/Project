package states;

import contracts.Projectile;
import game.GameEngine;
import gameobjects.BackGround;
import gameobjects.Explosion;
import gameobjects.ships.EnemyShip;
import gameobjects.ships.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GameState extends State {
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight());
    private BackGround backGround;

    private EnemyShip enemy = new EnemyShip(this.gameEngine.getGameWidth() / 2, 0);

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
        this.backGround = new BackGround(0, 0, this.gameEngine.getGameWidth(), this.gameEngine.getGameHeight(), 5);
    }

    @Override
    public void tick() {
        this.player.move(this.gameEngine.inputHandler);
        this.player.tick();
        this.backGround.tick();
        enemy.tick();

        if (this.player.getProjectiles().size() >= 1) {
            for (int index = 0; index < this.player.getProjectiles().size(); index++) {
                Projectile bullet =  this.player.getProjectiles().get(index);
                boolean intersects = this.player.getProjectiles().get(index).intersect(this.enemy.getBoundingBox());
                if (intersects) {
                    int currentHealth = this.enemy.getHealth() - bullet.getDamage();
                    enemy.setHealth(currentHealth);
                    enemy.setIsHit(true);
                    if (enemy.getHealth() <= 0) {
                        explosions.add(new Explosion(this.enemy.getX(), this.enemy.getY(), 50, 54));
                    }
                    this.player.getProjectiles().remove(index);
                }
            }
        }

        if (this.enemy.getProjectiles().size() > 1) {
            for (int index = 0; index < this.enemy.getProjectiles().size(); index++) {
                Projectile projectile = this.enemy.getProjectiles().get(index);
                if (projectile.intersect(this.player.getBoundingBox())) {
                    int currentHealth = this.player.getHealth() - projectile.getDamage();
                    this.enemy.getProjectiles().remove(index);
                    this.player.setHealth(currentHealth);
                }
            }
        }

        if (this.player.getProjectiles().size() >= 1) {
            for (int index = 0; index < this.player.getProjectiles().size(); index++) {
                if (this.player.getProjectiles().get(index).getY() <= 0) {
                    this.player.getProjectiles().remove(index);
                }
            }
        }

        if (this.enemy.getProjectiles().size() >= 1) {
            for (int index = 0; index < this.enemy.getProjectiles().size(); index++) {
                if (this.enemy.getProjectiles().get(index).getY() <= 0) {
                    this.enemy.getProjectiles().remove(index);
                }
            }
        }
        if (this.explosions.size() >= 1) {
            for (int i = 0; i < this.explosions.size(); i++) {
                this.explosions.get(i).tick();
            }
        }

        if (this.enemy.getY() >= 600 || this.enemy.getHealth() <= 0) {
            Random randomGenerator = new Random();
            int nextX = randomGenerator.nextInt(800);
            this.enemy = new EnemyShip(nextX, -40);
        }


    }

    @Override
    public void render(Graphics graphics) {
        this.backGround.render(graphics);
        if (this.enemy.getHealth() > 0) {
            this.enemy.render(graphics);
        }

        if (this.explosions.size() >= 1) {
            for (int idnex = 0; idnex < this.explosions.size(); idnex++) {
                if (!this.explosions.get(idnex).hasExploded) {
                    this.explosions.get(idnex).render(graphics);
                }
            }
        }

        this.player.render(graphics);
    }
}