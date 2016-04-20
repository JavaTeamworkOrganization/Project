package states;

import contracts.Projectile;
import game.GameEngine;
import gameobjects.BackGround;
import gameobjects.Explosion;
import gameobjects.ships.EnemyDestroyer;
import gameobjects.ships.EnemyShip;
import gameobjects.ships.RegularEnemy;
import gameobjects.ships.Player;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameState extends State {
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private LinkedHashSet<EnemyShip> enemyShips;
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight());
    private BackGround backGround;

    private Random randomEnemyXGenerator;
    private int regularEnemyRate = 70 ;
    private int fighterRate = 100;
    private int enemyGeneratingConter;

    private int secondsElapsed = 1;
    private int currentFrame;
    private int fps = 60;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
        this.randomEnemyXGenerator = new Random();
        this.backGround = new BackGround(0, 0, this.gameEngine.getGameWidth(), this.gameEngine.getGameHeight(), 3);
        this.enemyShips = new LinkedHashSet<>(100);
    }

    @Override
    public void tick() {

        this.updateShips();

        this.enemyGeneratingConter++;
        if (this.enemyGeneratingConter % this.regularEnemyRate == 0) {
            this.generateEnemies();
        }

        if (this.enemyGeneratingConter % this.fighterRate == 0) {
            int randomX = this.randomEnemyXGenerator.nextInt(this.gameEngine.getGameWidth() - EnemyDestroyer.DEFAULT_WIDTH);
            this.enemyShips.add(new EnemyDestroyer(randomX, 0 - EnemyDestroyer.DEFAULT_HEIGHT));
        }

        this.player.move(this.gameEngine.inputHandler);
        this.player.tick();
        this.backGround.tick();



        if (this.explosions.size() >= 0) {
            for (int i = 0; i < this.explosions.size(); i++) {
                this.explosions.get(i).tick();
            }
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.backGround.render(graphics);
        for (EnemyShip enemyShip : this.enemyShips) {
            enemyShip.render(graphics);
        }

        this.player.render(graphics);

        if (this.explosions.size() >= 0) {
            for (int idnex = 0; idnex < this.explosions.size(); idnex++) {
                if (!this.explosions.get(idnex).hasExploded) {
                    this.explosions.get(idnex).render(graphics);
                }
            }
        }

        graphics.setColor(Color.WHITE);
        graphics.drawString(String.format("%d", this.player.getHealth()), 10, 590);
    }

    protected void updateShips() {
        Iterator<EnemyShip> enemyShipIterator = this.enemyShips.iterator();
        while (enemyShipIterator.hasNext()) {
            EnemyShip enemy = enemyShipIterator.next();
            enemy.tick();

            this.intersectPlayerBullet(enemy);
            this.enemyBulletIntersectPlayer(enemy);
            this.changeDirection(enemy);


            if (enemy.getY() >= this.gameEngine.getGameHeight()) {
                enemyShipIterator.remove();
            }
        }
    }

    protected void generateEnemies() {
        int randomX = this.randomEnemyXGenerator.nextInt(this.gameEngine.getGameWidth() - 50);
        this.enemyShips.add(new RegularEnemy(randomX, -54));
    }

    protected void intersectPlayerBullet(EnemyShip enemy) {
        List<Projectile> playerBullets = this.player.getProjectiles();
        if (playerBullets.size() >= 0) {
            for (int playerBulletIndex = 0; playerBulletIndex < playerBullets.size(); playerBulletIndex++) {
                Projectile playerBullet = playerBullets.get(playerBulletIndex);
                boolean intersects = playerBullet.intersect(enemy.getBoundingBox());
                if (intersects && enemy.getIsAlive()) {
                    int currentHealth = enemy.getHealth() - playerBullet.getDamage();
                    enemy.setHealth(currentHealth);
                    enemy.setIsHit(true);
                    if (enemy.getHealth() <= 0) {
                        explosions.add(new Explosion(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()));
                        enemy.setIsHit(false);
                    }

                    this.player.getProjectiles().remove(playerBullet);
                }

                if (playerBullet.getY() >= this.gameEngine.getGameHeight()) {
                    this.player.getProjectiles().remove(playerBullet);
                }
            }
        }
    }

    protected void changeDirection(EnemyShip enemy) {
        if (enemy.getX() <= 0) {
            enemy.setXVelocity(Math.abs(enemy.getXVelocity()));
        } else if (enemy.getX() >= this.gameEngine.getGameWidth() - enemy.getWidth()) {
            enemy.setXVelocity(-(enemy.getXVelocity()));
        }
    }

    protected void enemyBulletIntersectPlayer(EnemyShip enemy) {
        for (int index = 0; index < enemy.getProjectiles().size(); index++) {
            Projectile projectile = enemy.getProjectiles().get(index);
            if (projectile.intersect(this.player.getBoundingBox())) {
                int currentHealth = this.player.getHealth() - projectile.getDamage();
                enemy.getProjectiles().remove(index);
                this.player.setHealth(currentHealth);
            }

            if (projectile.getY() >= this.gameEngine.getGameHeight()) {
                enemy.getProjectiles().remove(index);
            }
        }
    }
}