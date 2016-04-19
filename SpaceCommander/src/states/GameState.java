package states;

import contracts.Projectile;
import game.GameEngine;
import gameobjects.Explosion;
import gameobjects.ships.EnemyShip;
import gameobjects.ships.Player;
import gfx.ImageLoader;
import gfx.SpriteSheet;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private boolean hasToRender = true;
    private SpriteSheet backGround = new SpriteSheet(ImageLoader.loadImage("/bkg.jpg"));
    private int backGroundVelocity = 5;
    private int backGroundY = 600;
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight());
    private int x;
    private int y;

    private EnemyShip enemy = new EnemyShip(this.gameEngine.getGameWidth() / 2, 0);

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
    }

    @Override
    public void tick() {


        if (y == 7) {
            y = 0;
            x = 0;
            this.hasToRender = true;
        }
        this.player.move(this.gameEngine.inputHandler);
        this.player.tick();
        this.backGroundY -= this.backGroundVelocity;
        if (this.backGroundY < 0) {
            this.backGroundY = 600;
        }

        if (this.player.getProjectiles().size() >= 1) {
            for (int index = 0; index < this.player.getProjectiles().size(); index++) {
                Projectile bullet =  this.player.getProjectiles().get(index);
                boolean intersects = this.player.getProjectiles().get(index).intersect(this.enemy.getBoundingBox());
                if (intersects) {
                    int currentHealth = this.enemy.getHealth() - bullet.getDamage();
                    enemy.setHealth(currentHealth);
                    if (enemy.getHealth() <= 0) {
                        explosions.add(new Explosion(this.enemy.getX(), this.enemy.getY(), 40, 44));
                    }
                    this.player.getProjectiles().remove(index);
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
        if (this.explosions.size() >= 1) {
            for (int i = 0; i < this.explosions.size(); i++) {
                this.explosions.get(i).tick();
            }
        }


        enemy.tick();
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(backGround.crop(0, this.backGroundY, 800, 600), 0, 0, 800, 600, null);
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

        if (hasToRender) {
            this.player.render(graphics);
        }
    }
}