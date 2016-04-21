package states;

import contracts.Projectile;
import game.GameEngine;
import gameobjects.BackGround;
import gameobjects.Explosion;
import gameobjects.ships.EnemyDestroyer;
import gameobjects.ships.EnemyShip;
import gameobjects.ships.Player;
import gameobjects.ships.RegularEnemy;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameState extends State {
    private ArrayList<Explosion> explosions = new ArrayList<>();
    private LinkedHashSet<EnemyShip> enemyShips;
    private Player player = new Player(this.gameEngine.getGameWidth() / 2, this.gameEngine.getGameHeight());
    private BackGround backGround;

    private Random randomEnemyXGenerator;
    private int regularEnemyRate = 200;
    private int fighterRate = 400;
    private int minFighetrRate = 200;
    private int minRegularEnemyRate = 100;

    private int frameCounter = 1;

    public GameState(GameEngine gameEngine) {
        super(gameEngine);
        this.randomEnemyXGenerator = new Random();
        this.backGround = new BackGround(0, 0, this.gameEngine.getGameWidth(), this.gameEngine.getGameHeight(), 3);
        this.enemyShips = new LinkedHashSet<>(100);
    }

    @Override
    public void tick() {
        this.frameCounter++;
        if (this.frameCounter == Integer.MAX_VALUE) {
            this.frameCounter = 0;
        } else if (this.frameCounter % 600 == 0 && this.regularEnemyRate <= this.minRegularEnemyRate) {
            this.regularEnemyRate -= 10;
        } else if (this.frameCounter % 600 == 0 && this.fighterRate <= this.minFighetrRate) {
            this.fighterRate -= 10;
        }

        this.updateShips();

        if (this.frameCounter % this.regularEnemyRate == 0) {
            this.generateEnemies();
        }

        if (this.frameCounter % this.fighterRate == 0) {
            int randomX = this.randomEnemyXGenerator.nextInt(this.gameEngine.getGameWidth() - EnemyDestroyer.DEFAULT_WIDTH);
            this.enemyShips.add(new EnemyDestroyer(randomX, 0 - EnemyDestroyer.DEFAULT_HEIGHT));
        }

        if (this.frameCounter > 5) {
            this.player.move(this.gameEngine.inputHandler);
        }

        this.setPlayerBounds();
        this.player.tick();
        this.backGround.tick();

        if (this.explosions.size() >= 0) {
            for (int i = 0; i < this.explosions.size(); i++) {
                this.explosions.get(i).tick();
            }
        }

        if (this.player.getHealth() <= 0) {
            StateManager.setState(new GameOver(this.gameEngine, this.player.getScore()));
        }
    }

    @Override
    public void render(Graphics graphics) {
        this.backGround.render(graphics);

        for (EnemyShip enemyShip : this.enemyShips) {
            enemyShip.render(graphics);
        }

        graphics.setColor(new Color(255, 255, 255));
        graphics.fillRect(599, 9, this.player.getHealth() * 2 + 2, 27);
        if (this.player.getIsHit()) {
            graphics.setColor(new Color(255, 243, 22));
        } else {
            graphics.setColor(new Color(255, 35, 48));
        }

        graphics.fillRect(600, 10, this.player.getHealth() * 2, 25);
        graphics.setFont(new Font("Arial", Font.BOLD, 15));
        graphics.setColor(Color.WHITE);
        graphics.drawString("HP", 575, 29);
        graphics.drawString(String.format("%d", this.player.getHealth()), 600 + ((this.player.getHealth() * 2) / 2) - 4, 29);
        graphics.drawString(String.format("Score: %d", this.player.getScore()), 575, 60);

        this.player.render(graphics);

        if (this.explosions.size() >= 0) {
            for (int idnex = 0; idnex < this.explosions.size(); idnex++) {
                if (!this.explosions.get(idnex).hasExploded) {
                    this.explosions.get(idnex).render(graphics);
                }
            }
        }
    }

    protected void updateShips() {
        Iterator<EnemyShip> enemyShipIterator = this.enemyShips.iterator();
        while (enemyShipIterator.hasNext()) {
            EnemyShip enemy = enemyShipIterator.next();
            enemy.tick();
            if (enemy.intersect(this.player.getBoundingBox()) && enemy.getIsAlive()) {
                this.explosions.add(new Explosion(enemy.getX(), enemy.getY(), enemy.getWidth(), enemy.getHeight()));
                int playerCurrentHealth = this.player.getHealth() - enemy.getHealth();
                this.player.setHealth(playerCurrentHealth);
                this.player.setScore(this.player.getScore() + enemy.getScorePoints());
                enemy.setIsAlive(false);
            }

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
                        this.player.setScore(this.player.getScore() + enemy.getScorePoints());
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
                this.player.setIsHit(true);
            }

            if (projectile.getY() >= this.gameEngine.getGameHeight()) {
                enemy.getProjectiles().remove(index);
            }
        }
    }

    private void setPlayerBounds() {
        if (this.player.getX() <= 5) {
            this.player.setX(5);
        } else if (this.player.getX() >= this.gameEngine.getGameWidth() - this.player.getWidth() + 5) {
            this.player.setX(this.gameEngine.getGameWidth() - this.player.getWidth() + 5);
        }
    }
}